package com.softserveinc.ita.rozetka.data.subcategory;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlcoholicBeveragesAndProductsSubcategory implements ISubcategory {

    COGNAC("konyak-i-brendi", "Коньяк"),
    WHISKY("viski-odnosolodoviy", "Віскі односолодовий");

    @NonNull
    private final String subcategoryXpath;
    @NonNull
    private final String subcategoryNameUa;
}