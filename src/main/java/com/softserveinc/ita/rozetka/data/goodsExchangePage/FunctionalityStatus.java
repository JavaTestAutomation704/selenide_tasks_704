package com.softserveinc.ita.rozetka.data.goodsExchangePage;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FunctionalityStatus {

    ALL_FUNCTION_WORKS(1, "All functions work properly"),
    FUNCTION_NOT_WORKS(2, "One or more functions are not working");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}
