package com.softserveinc.ita.rozetka.data.profile;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommunicationLanguage {

    UKRAINIAN("Українська"),
    RUSSIAN("Русский");

    @NonNull
    private final String name;

    public static CommunicationLanguage getByValue(String value) {
        for (var communicationLanguage : values()) {
            if (communicationLanguage.name.equals(value)) {
                return communicationLanguage;
            }
        }
        throw new IllegalArgumentException("Can't get CommunicationLanguage enum for value: " + value);
    }
}