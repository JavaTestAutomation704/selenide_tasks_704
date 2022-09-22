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
}