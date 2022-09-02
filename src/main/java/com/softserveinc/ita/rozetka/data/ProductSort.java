package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductSort {

    PRICE_DESCENDING("expensive"),
    PRICE_ASCENDING("cheap"),
    POPULARITY("popularity"),
    NEWEST("novelty"),
    PROMOTION("action"),
    RATING("rank");

    @NonNull
    private final String optionXpath;
}