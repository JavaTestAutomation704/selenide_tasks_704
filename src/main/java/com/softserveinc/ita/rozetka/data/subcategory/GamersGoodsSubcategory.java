package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GamersGoodsSubcategory implements ISubcategory {

    MONITORS("monitors");

    @NonNull
    private final String subcategoryXpath;
}