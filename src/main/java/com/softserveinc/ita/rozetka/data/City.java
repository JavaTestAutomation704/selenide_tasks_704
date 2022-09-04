package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum City {

    LVIV("Львів"),
    DNIPRO("Дніпро");

    @NonNull
    private final String city;
}
