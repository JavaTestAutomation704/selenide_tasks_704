package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {

    PICK_UP_FROM_ROZETKA("Самовивіз з наших магазинів"),
    COURIER_TO_YOUR_ADDRESS("Кур'єр на вашу адресу"),
    PICK_UP_FROM_MOBILE_POINTS("Самовивіз з мобільних точок видачі"),
    PICK_UP_FROM_MEEST("Самовивіз з Meest"),
    PICK_UP_FROM_UKRPOSHTA("Самовивіз з Укрпошта"),
    PICK_UP_FROM_NOVAPOSHTA("Самовивіз з Нової Пошти"),
    COURIER_MEEST("Кур'єр Meest");

    @NonNull
    private final String deliveryNameUa;
}