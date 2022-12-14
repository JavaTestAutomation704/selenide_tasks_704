package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SmartphonesTvAndElectronicsSubcategory implements ISubcategory {

    MOBILE_PHONES("mobile", "Мобільні телефони");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}