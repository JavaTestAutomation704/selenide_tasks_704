package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LaptopsAndComputersSubcategory implements ISubcategory {

    NOTEBOOKS("/notebooks", "Ноутбуки"),
    ASUS("asus", "Asus"),
    COMPUTERS("/computers/", "Комп'ютери"),
    MONITORS("monitors", "Монітори"),
    TABLETS("tablets", "Планшети"),
    PROCESSORS("processors", "Процесори");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}