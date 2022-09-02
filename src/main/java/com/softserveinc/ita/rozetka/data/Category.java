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
    SMARTPHONES_TV_AND_ELECTRONICS("telefony");

    @NonNull
    private final String categoryXpath;
}
