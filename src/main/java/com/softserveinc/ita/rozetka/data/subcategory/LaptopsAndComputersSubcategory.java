package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LaptopsAndComputersSubcategory implements ISubcategory {

    NOTEBOOKS("/notebooks"),
    ASUS("asus"),
    COMPUTERS_("/computers/"),
    MONITORS("monitors");

    @NonNull
    private final String subcategoryXpath;
}