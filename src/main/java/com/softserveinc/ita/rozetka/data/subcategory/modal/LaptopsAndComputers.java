package com.softserveinc.ita.rozetka.data.subcategory.modal;

import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LaptopsAndComputers implements ISubcategory {

    NOTEBOOKS("/notebooks"),
    ASUS("asus"),
    COMPUTERS_("/computers/"),
    MONITORS("monitors");

    @NonNull
    private final String subcategoryXpath;

    @Override
    public String getSubcategoryXpath() {
        return subcategoryXpath;
    }
}