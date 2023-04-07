package com.valkryst.VCodeLanguageDetection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeFilterTest {
    @Test
    public void testFilterBraces() {
        final var input = "Hello, (Wor[ld])! How {are} you?";
        final var expected = "Hello,  Wor ld  ! How  are  you?";
        final var actual = CodeFilter.filterBraces(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFilterQuotedStrings() {
        final var input = "Hello, \"Wor'ld\"! How 'are' you?";
        final var expected = "Hello,  ! How   you?";
        final var actual = CodeFilter.filterQuotedStrings(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFilterSymbols() {
        final var input = "Hello, ~@#W$o^r&ld*-_=+;:'\"/\\!";
        final var expected = "Hello,    W o r ld -_    '\"   ";
        final var actual = CodeFilter.filterSymbols(input);
        Assertions.assertEquals(expected, actual);
    }
}
