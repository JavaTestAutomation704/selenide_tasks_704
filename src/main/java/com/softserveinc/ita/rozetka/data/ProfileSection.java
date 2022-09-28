package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProfileSection {

    PERSONAL_DATA("Особисті дані"),
    DELIVERY_ADDRESS("Адреса доставки");

    @NonNull
    private final String name;
}