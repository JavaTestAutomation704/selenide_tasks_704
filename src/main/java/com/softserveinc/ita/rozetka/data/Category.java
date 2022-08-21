package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    NOTEBOOKS_COMPUTERS("computers-notebooks"),
    PHONES_TV_ELECTRONICS("telefony-tv-i-ehlektronika"),
    GAMER_PRODUCTS("game-zone");

    @NonNull private final String categoryXpath;
}
