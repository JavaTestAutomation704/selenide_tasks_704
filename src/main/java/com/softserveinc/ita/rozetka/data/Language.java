package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {

    UA("UA"),
    RU("RU");

    @NonNull
    private final String language;

    public static Language getByValue(String value) {
        for (var language : values()) {
            if (language.language.equals(value)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Can't get Language enum for value: " + value);
    }
}