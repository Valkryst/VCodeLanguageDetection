package com.valkryst.VCodeLanguageDetection.language;

/**
 * Represents the Java programming language.
 *
 * Aliases and Keywords were taken from {@code highlight.js/src/languages/java.js}.
 */
public class Java extends Language {
    private static final String[] KEYWORDS = {
        // Main Keywords
        "abstract",
        "assert",
        "break",
        "case",
        "catch",
        "const ",
        "continue",
        "default",
        "do",
        "else",
        "enum",
        "exports",
        "final",
        "finally",
        "for",
        "if",
        "import",
        "instanceof",
        "module",
        "native",
        "package",
        "permits",
        "private",
        "protected",
        "public",
        "requires",
        "sealed",
        "static",
        "strictfp",
        "switch",
        "synchronized",
        "throws",
        "transient",
        "try",
        "var",
        "void",
        "volatile",
        "while",
        "yield",

        // Built Ins
        "super",
        "this",

        // Literals
        "false",
        "null",
        "true",

        // Types
        "boolean",
        "byte",
        "char",
        "double",
        "float",
        "int",
        "long",
        "short"
    };
}
