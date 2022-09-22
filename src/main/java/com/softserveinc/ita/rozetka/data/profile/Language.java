package com.softserveinc.ita.rozetka.data.profile;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {

    UA("Українська"),
    RU("Русский");

    @NonNull
    private final String name;
}