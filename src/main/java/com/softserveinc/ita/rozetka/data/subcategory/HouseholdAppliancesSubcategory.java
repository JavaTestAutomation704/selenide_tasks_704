package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HouseholdAppliancesSubcategory implements ISubcategory {

    REFRIGERATORS("refrigerators");

    @NonNull
    private final String subcategoryXpath;
}