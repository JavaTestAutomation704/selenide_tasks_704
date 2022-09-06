package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostOffice {

    MEEST("Meest"),
    UKRPOSHTA("Укрпошта"),
    NOVAPOSHTA("Нова Пошта"),
    MOBILE_POINT("Мобільна точка"),
    OUR_STORE("Наш магазин");

    @NonNull
    private final String postName;
}
