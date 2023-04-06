package com.valkryst.VCodeLanguageDetection;

import com.valkryst.VCodeLanguageDetection.language.*;

import java.util.Optional;
import java.util.regex.Pattern;

public class LanguageDetector {
    /** Pre-compiled regex pattern to remove non-alphanumeric characters, while allowing a few symbols. */
    private final Pattern NON_ALPHANUMERIC_PATTERN = Pattern.compile("[^a-zA-Z0-9_\\->*|<=?!]");

    /** Pre-compiled regex pattern to remove quoted strings. */
    private final Pattern QUOTED_STRINGS_PATTERN = Pattern.compile("(\"[^\"]*\")|('[^']*')");

    /** The list of supported languages. */
    private static final Language[] LANGUAGES = {
        new C(),
        new Clojure(),
        new CPlusPlus(),
        new CSharp(),
        new D(),
        new Dart(),
        new Java(),
        new JavaScript(),
        new Perl(),
        new PHP(),
        new Python()
    };

    /** The singleton instance. */
    private static LanguageDetector instance;

    /** Private constructor to prevent instantiation. */
    private LanguageDetector() {}

    /**
     * Detects the language of the code.
     *
     * @param code The code to detect the language of.
     *
     * @return The detected language, or an empty optional if the language could not be detected.
     */
    public Optional<Language> detect(String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }

        code = stripQuotedStrings(code);
        code = stripNonAlphanumericCharacters(code);

        float bestScore = 0;
        Language bestMatch = null;

        for (final var language : LANGUAGES) {
            float score = matchScore(code, language);

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
     * @param language The language to match.
     * @return The match score.
     */
    private float matchScore(final String code, final Language language) {
        final var tokens = code.split("\\s+");
        float score = 0;

        for (final var keyword : language.getKeywords()) {
            for (final var token : tokens) {
                if (token.equals(keyword)) {
                    score += 1.0;
                }
            }
        }

        // normalize score by length of tokens
        return score / tokens.length;
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
    public static LanguageDetector getInstance() {
        if (instance == null) {
            instance = new LanguageDetector();
        }

        return instance;
    }
}
