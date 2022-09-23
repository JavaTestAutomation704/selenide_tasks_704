package com.softserveinc.ita.rozetka.data.profile;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("Чоловіча"),
    FEMALE("Жіноча");

    @NonNull
    private final String name;

    public static Gender getByValue(String value) {
        for (var gender : values()) {
            if (gender.name.equals(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Can't get Gender enum for value: " + value);
    }
}