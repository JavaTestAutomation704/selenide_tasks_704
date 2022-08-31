package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PhonesTvElectronicsSubcategory implements ISubcategory {

    CAMERAS("photo/", "Фотоапарати");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryUkrainianName;
}