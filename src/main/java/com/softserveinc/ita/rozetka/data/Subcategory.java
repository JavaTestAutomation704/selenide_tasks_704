package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Subcategory {

    NOTEBOOKS("/notebooks"),
    COMPUTERS_("/computers/"),
    MONITORS("monitors"),

    //inner subcategories
    ASUS("asus"),
    REFRIGERATORS("refrigerators");

    @NonNull
    private final String subcategoryXpath;
}
