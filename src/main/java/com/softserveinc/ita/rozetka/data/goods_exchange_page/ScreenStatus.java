package com.softserveinc.ita.rozetka.data.goods_exchange_page;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScreenStatus {

    NO_USE(1, "There are absolutely no signs of use"),
    DAMAGED(5, "Chips, cracks, broken anti-glare coating");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}