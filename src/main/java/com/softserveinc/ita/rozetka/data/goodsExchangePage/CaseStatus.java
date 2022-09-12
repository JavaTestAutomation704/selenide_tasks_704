package com.softserveinc.ita.rozetka.data.goodsExchangePage;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CaseStatus {

    NO_USE_SIGNS(1, "There are absolutely no signs of use"),
    BROKEN_OR_DEFORMED(5, "Broken or deformed");

    private final int number;

    @NonNull
    private final String name;
}
