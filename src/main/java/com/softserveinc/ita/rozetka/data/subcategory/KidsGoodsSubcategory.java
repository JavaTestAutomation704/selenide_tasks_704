package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KidsGoodsSubcategory implements ISubcategory {

    KIDS_CONSTRUCTION_SETS("building_kits", "Дитячі конструктори"),
    TABLE_GAMES("nastoljnye-igry-i-golovolomki", "Настільні ігри");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}