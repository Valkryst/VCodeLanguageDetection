package com.valkryst.VCodeLanguageDetection;

import java.util.regex.Pattern;

public class CodeFilter {
    private static final Pattern QUOTED_STRINGS_PATTERN = Pattern.compile("\"(.*?)\"|'(.*?)'");

    /**
     * Removes all braces, brackets, and parentheses from a {@link String}.
     *
     * @param string The string to filter.
     * @return The filtered string.
     */
    public static String filterBraces(final String string) {
        return string.replace('[', ' ')
                .replace(']', ' ')
                .replace('{', ' ')
                .replace('}', ' ')
                .replace('(', ' ')
                .replace(')', ' ');
    }

    /**
     * Removes all quoted strings from a {@link String}.
     *
     * @param string The string to filter.
     *
     * @return The filtered string.
     */
    public static String filterQuotedStrings(final String string) {
        return QUOTED_STRINGS_PATTERN.matcher(string).replaceAll(" ");
    }

    /**
     * Removes the ~, `, !, @, #, $, %, ^, &, *, +, =, ;, :, \, and / characters from a {@link String}.
     *
     * @param string The string to filter.
     * @return The filtered string.
     */
    public static String filterSymbols(final String string) {
        return string.replace('~', ' ')
                .replace('`', ' ')
                .replace('!', ' ')
                .replace('@', ' ')
                .replace('#', ' ')
                .replace('$', ' ')
                .replace('%', ' ')
                .replace('^', ' ')
                .replace('&', ' ')
                .replace('*', ' ')
                .replace('+', ' ')
                .replace('=', ' ')
                .replace(';', ' ')
                .replace(':', ' ')
                .replace('\\', ' ')
                .replace('/', ' ');
    }
}