package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CloseShoesAccessoriesSubcategory implements ISubcategory {

    MENS_JEANS("mugskie-dginsi", "Чоловічі джинси"),
    MENS_SNEAKERS("mugskie-kedi", "Чоловічі кеди"),
    MENS_TSHIRTS_AND_SHIRTS("mugskie-rybashki-futbolki-i-mayki", "Футболки та сорочки");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}