package com.softserveinc.ita.rozetka.data.goodsExchangePage;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageStatus {

    PERFECT_IMAGE(1, "A perfect image without defects"),
    BROKEN_COLOR_RENDERING(4, "Color reproduction is broken");

    private final int orderNumber;

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}
