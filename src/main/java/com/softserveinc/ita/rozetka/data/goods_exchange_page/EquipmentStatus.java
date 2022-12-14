package com.softserveinc.ita.rozetka.data.goods_exchange_page;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EquipmentStatus {

    FULL_EQUIPMENT(1, "Full factory equipment without damage"),
    COMPLETE_SET_IS_MISSING(3, "There is no complete set");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}