package com.softserveinc.ita.rozetka.data.goods_exchange_page;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CaseStatus {

    NO_USE_SIGNS(1, "There are absolutely no signs of use"),
    BROKEN_OR_DEFORMED(5, "Broken or deformed");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}