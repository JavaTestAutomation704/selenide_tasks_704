package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OfficeSchoolBooksSubcategory implements ISubcategory {

    PENS("/ruchki/", "ручки");

    @NonNull
    private final String subcategoryXpath;

    @NonNull
    private final String subcategoryNameUa;
}