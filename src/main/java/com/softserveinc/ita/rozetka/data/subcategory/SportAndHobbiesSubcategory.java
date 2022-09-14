package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SportAndHobbiesSubcategory implements ISubcategory {

    FISHING_RODS("rods", "Вудлища"),
    HOVERBOARDS("girobordi", "Гіроборди"),
    YOGA("yoga", "Йога");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}