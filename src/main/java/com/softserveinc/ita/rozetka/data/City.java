package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum City {

    LVIV("lviv", "Львів", "Львов"),
    DNIPRO("dnipro", "Дніпро", "Днепр");

    @NonNull
    private final String city;
    @NonNull
    private final String cityNameUa;
    @NonNull
    private final String cityNameRu;

    @Override
    public String toString() {
        return "Сity '" + city + '\'';
    }
}