package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Country {

    SPAIN("Іспанія"),
    ITALY("Італія");

    @NonNull
    private final String countryNameUa;
}