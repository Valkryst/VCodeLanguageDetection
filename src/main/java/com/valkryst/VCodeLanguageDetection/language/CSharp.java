package com.valkryst.VCodeLanguageDetection.language;

/**
 * Represents the C# programming language.
 *
 * Aliases and Keywords were taken from {@code highlight.js/src/languages/csharp.js}.
 */
public class CSharp extends Language {
    private static final String[] KEYWORDS = {
        // Main Keywords
        "abstract",
        "as",
        "base",
        "break",
        "case",
        "catch",
        "class",
        "const",
        "continue",
        "do",
        "else",
        "event",
        "explicit",
        "extern",
        "finally",
        "fixed",
        "for",
        "foreach",
        "goto",
        "if",
        "implicit",
        "in",
        "interface",
        "internal",
        "is",
        "lock",
        "namespace",
        "new",
        "operator",
        "out",
        "override",
        "params",
        "private",
        "protected",
        "public",
        "readonly",
        "record",
        "ref",
        "return",
        "scoped",
        "sealed",
        "sizeof",
        "stackalloc",
        "static",
        "struct",
        "switch",
        "this",
        "throw",
        "try",
        "typeof",
        "unchecked",
        "unsafe",
        "using",
        "virtual",
        "void",
        "volatile",
        "while",
        
        // Built Ins
        "bool",
        "byte",
        "char",
        "decimal",
        "delegate",
        "double",
        "dynamic",
        "enum",
        "float",
        "int",
        "long",
        "nint",
        "nuint",
        "object",
        "sbyte",
        "short",
        "string",
        "uint",
        "ulong",
        "ushort",
        
        // Contextual Keywords
        "add",
        "alias",
        "and",
        "ascending",
        "async",
        "await",
        "by",
        "descending",
        "equals",
        "from",
        "get",
        "global",
        "group",
        "init",
        "into",
        "join",
        "let",
        "nameof",
        "not",
        "notnull",
        "on",
        "or",
        "orderby",
        "partial",
        "remove",
        "select",
        "set",
        "unmanaged",
        "value|0",
        "var",
        "when",
        "where",
        "with",
        "yield",
        
        // Function Modifiers
        "abstract",
        "async",
        "extern",
        "internal",
        "new",
        "override",
        "partial",
        "private",
        "protected",
        "public",
        "sealed",
        "static",
        "unsafe",
        "virtual",
        
        // Literals
        "default",
        "false",
        "null",
        "true"
    };
}