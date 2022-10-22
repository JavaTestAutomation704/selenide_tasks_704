package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    LAPTOPS_AND_COMPUTERS("computers-notebooks"),
    PHONES_TV_ELECTRONICS("telefony-tv-i-ehlektronika"),
    SMARTPHONES_TV_AND_ELECTRONICS("telefony"),
    GAMERS_GOODS("game-zone"),
    HOUSEHOLD_APPLIANCES("bt"),
    PLUMBING_AND_REPAIR("santekhnika-i-remont"),
    COTTAGE_GARDEN_BACKYARD("dacha-sad-ogorod"),
    SPORT_AND_HOBBIES("sport-i-uvlecheniya"),
    CLOSE_SHOES_ACCESSORIES("shoes_clothes"),
    KIDS_GOODS("kids"),
    OFFICE_SCHOOL_BOOKS("office-school-books"),
    ALCOHOLIC_BEVERAGES_AND_PRODUCTS("alkoholnie-napitki-i-produkty"),
    BUSINESS_GOODS("tovary-dlya-biznesa"),
    PROMOTION("promo");

    @NonNull
    private final String categoryXpath;
}