package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {

    ROZETKA_PICK_UP("Самовивіз з наших магазинів"),
    MEEST_PICK_UP("Самовивіз з Meest"),
    NOVA_POSHTA_PICK_UP("Самовивіз з Нової Пошти");

    @NonNull
    private final String deliveryNameUa;
}