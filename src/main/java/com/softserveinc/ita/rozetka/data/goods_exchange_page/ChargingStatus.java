package com.softserveinc.ita.rozetka.data.goods_exchange_page;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChargingStatus {

    LESS_THAN_HUNDRED_RECHARGE_CYCLES(1, "Less than 100 recharge cycles"),
    MORE_THEN_SEVEN_HUNDRED_RECHARGE_CYCLES(5, "More than 750 recharge cycles");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}