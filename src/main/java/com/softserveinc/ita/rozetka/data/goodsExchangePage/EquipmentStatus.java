package com.softserveinc.ita.rozetka.data.goodsExchangePage;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EquipmentStatus {

    FULL_EQUIPMENT(1, "Full factory equipment without damage"),
    COMPLETE_SET_IS_MISSING(3, "There is no complete set");

    private final int number;

    @NonNull
    private final String name;
}
