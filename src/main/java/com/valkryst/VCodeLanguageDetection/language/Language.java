package com.valkryst.VCodeLanguageDetection.language;

import lombok.SneakyThrows;

public abstract class Language {
    /**
     * Retrieves the {@code Language}'s name.
     *
     * @return The {@code Language}'s name.
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Retrieves the {@code Language}'s keywords.
     *
     * @return The {@code Language}'s keywords.
     */
    @SneakyThrows
    public String[] getKeywords() {
        final var field = getClass().getDeclaredField("KEYWORDS");
        field.setAccessible(true);
        return (String[]) field.get(this);
    }
}
