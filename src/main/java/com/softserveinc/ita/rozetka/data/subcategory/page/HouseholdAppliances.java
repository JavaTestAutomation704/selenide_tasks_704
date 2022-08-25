package com.softserveinc.ita.rozetka.data.subcategory.page;

import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HouseholdAppliances implements ISubcategory {

    REFRIGERATORS("refrigerators");

    @NonNull
    private final String subcategoryXpath;
}