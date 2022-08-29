package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    LAPTOPS_AND_COMPUTERS("computers-notebooks"),
    PHONES_TV_ELECTRONICS("telefony-tv-i-ehlektronika"),
    GOODS_FOR_GAMER("game-zone"),
    HOUSEHOLD_APPLIANCES("bt");

    @NonNull
    private final String categoryXpath;
}
