package com.valkryst.VCodeLanguageDetection;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

public class LanguageDetector {
    /** The singleton instance. */
    private static LanguageDetector instance;

    /** The map of languages and their keywords. */
    private final HashMap<String, String[]> keywordsMap;

    /** The map of keywords and their uniqueness. */
    private final HashMap<String, Double> keywordUniqueness = new HashMap<>();

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws IOException If an I/O error occurs.
     */
    private LanguageDetector() throws IOException {
        keywordsMap = loadKeywords();

        // Calculate the uniqueness of each keyword.
        for (final var language : keywordsMap.keySet()) {
            for (final var keyword : keywordsMap.get(language)) {
                int occurrences = 1;

                for (final var otherLanguage : keywordsMap.keySet()) {
                    if (otherLanguage.equals(language)) {
                        continue;
                    }

                    for (final var otherKeyword : keywordsMap.get(otherLanguage)) {
                        if (otherKeyword.equals(keyword)) {
                            occurrences++;
                        }
                    }
                }

                keywordUniqueness.put(keyword, 1.0 / occurrences);
            }
        }
    }

    /**
     * Detects the language of the code.
     *
     * @param code The code to detect the language of.
     *
     * @return The detected language, or an empty optional if the language could not be detected.
     */
    public Optional<String> detect(String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }

        code = CodeFilter.filterBraces(code);
        code = CodeFilter.filterSymbols(code);
        code = code.toLowerCase();

        float bestScore = 0;
        String bestMatch = null;

        for (final var language : keywordsMap.keySet()) {
            float score = matchScore(code, keywordsMap.get(language));

            if (score > bestScore) {
                bestScore = score;
                bestMatch = language;
            }
        }

        return Optional.ofNullable(bestMatch);
    }

    /**
     * Calculates the match score for the code and language.
     *
     * @param code The code to match.
     * @param keywords The keywords to match.
     * @return The match score.
     */
    private float matchScore(final String code, final String[] keywords) {
        final var tokens = code.split("\\s+");
        float score = 0;

        for (final var keyword : keywords) {
            for (final var token : tokens) {
                if (token.equals(keyword)) {
                    score += keywordUniqueness.get(keyword);
                }
            }
        }

        return score;
    }

    /**
     * Loads the keywords from the keywords folder, within the JAR.
     *
     * @return The map of languages and their keywords.
     *
     * @throws IOException If an I/O error occurs.
     */
    private HashMap<String, String[]> loadKeywords() throws IOException {
        final var fileNames = new String[] {
            "C", "Clojure", "CPlusPlus", "CSharp",
            "D", "Dart", "Delphi",
            "Fortran",
            "Go",
            "Java", "JavaScript",
            "Lua",
            "Perl", "PHP", "Python",
            "Ruby",
            "SQL"
        };

        final var keywordsMap = new HashMap<String, String[]>();

        for (final var fileName : fileNames) {
            final var inputStream = LanguageDetector.class.getResourceAsStream("/keywords/" + fileName);
            if (inputStream == null) {
                continue;
            }

            try (
                final var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                final var bufferedReader = new BufferedReader(inputStreamReader);
            ) {
                final var keywords = bufferedReader.lines()
                        .map(String::trim)
                        .map(String::toLowerCase)
                        .filter(line -> !line.isEmpty())
                        .filter(line -> !line.startsWith("//"));

                keywordsMap.put(fileName, keywords.toArray(String[]::new));
            } finally {
                inputStream.close();
            }
        }

        return keywordsMap;
    }

    /**
     * Retrieves the singleton instance.
     *
     * @return The singleton instance.
     */
    @SneakyThrows
    public static LanguageDetector getInstance() {
        if (instance == null) {
            instance = new LanguageDetector();
        }

        return instance;
    }
}
