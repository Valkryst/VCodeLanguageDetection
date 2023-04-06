package com.valkryst.VCodeLanguageDetection.language;

/**
 * Represents the Delphi programming language.
 *
 * Aliases and Keywords were taken from {@code highlight.js/src/languages/delphi.js}.
 */
public class Ruby extends Language {
    private static final String[] KEYWORDS = {
        // Main Keywords
        "__ENCODING__",
        "__FILE__",
        "__LINE__",
        "alias",
        "and",
        "BEGIN",
        "begin",
        "break",
        "case",
        "class",
        "defined",
        "do",
        "else",
        "elsif",
        "END",
        "end",
        "ensure",
        "extend",
        "for",
        "if",
        "in",
        "include",
        "module",
        "next",
        "not",
        "or",
        "prepend",
        "private",
        "protected",
        "public",
        "raise",
        "redo",
        "require",
        "rescue",
        "retry",
        "return",
        "self",
        "super",
        "then",
        "throw",
        "undef",
        "unless",
        "until",
        "when",
        "while",
        "yield",

        // Built Ins
        "attr_accessor",
        "attr_reader",
        "attr_writer",
        "define_method",
        "lambda",
        "module_function",
        "private_constant",
        "proc",

        // Literals
        "false",
        "nil",
        "true"
    };
}
