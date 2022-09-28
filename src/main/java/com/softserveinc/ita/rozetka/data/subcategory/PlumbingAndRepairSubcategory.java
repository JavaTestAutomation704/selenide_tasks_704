package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlumbingAndRepairSubcategory implements ISubcategory {

    BATHROOM_FURNITURE("mebel-dlya-vannoy-komnaty", "Меблі для ванної кімнати");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}