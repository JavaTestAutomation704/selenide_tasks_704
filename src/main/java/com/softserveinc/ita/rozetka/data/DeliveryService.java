package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryService {

    MEEST("Meest"),
    NOVA_POSHTA("Нова Пошта"),
    ROZETKA("Наш магазин");

    @NonNull
    private final String nameUa;
}