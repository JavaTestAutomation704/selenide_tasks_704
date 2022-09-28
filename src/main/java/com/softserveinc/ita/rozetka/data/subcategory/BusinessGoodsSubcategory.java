package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessGoodsSubcategory implements ISubcategory {

    CASH_BOXES("keshboksi", "кешбокси");

    @NonNull
    private final String subcategoryXpath;

    @NonNull
    private final String subcategoryNameUa;
}