package com.valkryst.VCodeLanguageDetection;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class LanguageDetector {
    /** Pre-compiled regex pattern to remove non-alphanumeric characters, while allowing a few symbols. */
    private final Pattern NON_ALPHANUMERIC_PATTERN = Pattern.compile("[^a-zA-Z0-9_\\->*|<=?!]");

    /** Pre-compiled regex pattern to remove quoted strings. */
    private final Pattern QUOTED_STRINGS_PATTERN = Pattern.compile("(\"[^\"]*\")|('[^']*')");

    /** The singleton instance. */
    private static LanguageDetector instance;

    /** The map of languages and their keywords. */
    private final HashMap<String, String[]> keywordsMap;

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws IOException If an I/O error occurs.
     */
    private LanguageDetector() throws IOException {
        keywordsMap = loadKeywords();
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

        code = stripQuotedStrings(code);
        code = stripNonAlphanumericCharacters(code);
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
            // todo Ensure all tokens are lowercased, preferably where they're defined,
            // todo to potentially improve detection results.
            for (final var token : tokens) {
                if (token.equals(keyword)) {
                    score += 1.0;
                }
            }
        }

        // normalize score by length of tokens
        return score / tokens.length;
    }

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
     * Strips all non-alphanumeric characters from the string.
     *
     * @param s The string to strip.
     *
     * @return The stripped string.
     */
    private String stripNonAlphanumericCharacters(final String s) {
        return NON_ALPHANUMERIC_PATTERN.matcher(s).replaceAll(" ");
    }

    /**
     * Strips all quoted strings from the string.
     *
     * @param s The string to strip.
     *
     * @return The stripped string.
     */
    private String stripQuotedStrings(final String s) {
        return QUOTED_STRINGS_PATTERN.matcher(s).replaceAll("");
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
