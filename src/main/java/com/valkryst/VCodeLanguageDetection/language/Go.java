package com.valkryst.VCodeLanguageDetection.language;

/**
 * Represents the Go programming language.
 *
 * Aliases and Keywords were taken from {@code highlight.js/src/languages/go.js}.
 */
public class Go extends Language {
    private static final String[] KEYWORDS = {
            // Main Keywords
            "break",
            "case",
            "chan",
            "const",
            "continue",
            "default",
            "defer",
            "else",
            "fallthrough",
            "for",
            "func",
            "go",
            "goto",
            "if",
            "import",
            "interface",
            "map",
            "package",
            "range",
            "return",
            "select",
            "struct",
            "switch",
            "type",
            "var",

            // Built Ins
            "append",
            "cap",
            "close",
            "complex",
            "copy",
            "delete",
            "imag",
            "len",
            "make",
            "new",
            "panic",
            "print",
            "println",
            "real",
            "recover",

            // Literals
            "false",
            "iota",
            "nil",
            "true",

            // Types
            "bool",
            "byte",
            "complex64",
            "complex128",
            "error",
            "float32",
            "float64",
            "int8",
            "int16",
            "int32",
            "int64",
            "int",
            "rune",
            "string",
            "uint8",
            "uint16",
            "uint32",
            "uint64",
            "uint",
            "uintptr"
    };
}
