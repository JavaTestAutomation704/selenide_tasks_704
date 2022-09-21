package com.softserveinc.ita.rozetka.data.goods_exchange_page;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExchangeProduct {

    OTHER_GOODS(2, "Other goods");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}
