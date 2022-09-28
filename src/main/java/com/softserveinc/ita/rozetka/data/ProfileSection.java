package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProfileSection {

    PERSONAL_DATA("Особисті дані"),
    DELIVERY_ADDRESS("Адреса доставки"),
    MY_ORDER_RECIPIENTS("Мої отримувачі замовлень");

    @NonNull
    private final String name;
}