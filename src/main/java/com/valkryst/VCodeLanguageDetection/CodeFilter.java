package com.valkryst.VCodeLanguageDetection;

public class CodeFilter {
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