package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryPickup {

    MEEST("Meest"),
    UKRPOSHTA("Укрпошта"),
    NOVAPOSHTA("Нова Пошта"),
    ROZETKA("Наш магазин");

    @NonNull
    private final String postNameUa;
}
