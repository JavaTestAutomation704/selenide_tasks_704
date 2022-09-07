package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    LAPTOPS_AND_COMPUTERS("computers-notebooks"),
    PHONES_TV_ELECTRONICS("telefony-tv-i-ehlektronika"),
    GAMERS_GOODS("game-zone"),
    HOUSEHOLD_APPLIANCES("bt"),
    SMARTPHONES_TV_AND_ELECTRONICS("telefony"),
    SPORT_AND_HOBBIES("sport-i-uvlecheniya"),
    ALCOHOLIC_BEVERAGES_AND_PRODUCTS("alkoholnie-napitki-i-produkty"),
    COTTAGE_GARDEN_BACKYARD("dacha-sad-ogorod"),
    PLUMBING_AND_REPAIR("santekhnika-i-remont"),
    OFFICE_SCHOOL_BOOKS("office-school-books"),
    BUSINESS_GOODS("tovary-dlya-biznesa");

    @NonNull
    private final String categoryXpath;
}
