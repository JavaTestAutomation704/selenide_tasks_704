package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductFilter {

    AVAILABLE("Є в наявності"),
    RUNNING_OUT("Закінчується"),
    OUT_OF_STOCK("Немає в наявності"),
    WHITE_COLOR("Білий"),
    ROZETKA_SELLER("Rozetka"),
    OTHER_SELLERS("Інші продавці"),
    NEW("Новий"),
    PRE_USED("Б/в"),
    PROMOTION("Акція"),
    WITH_BONUS("З бонусами"),
    MICROSOFT_BRAND("Microsoft"),
    COUNTRY_SPAIN("Іспанія"),
    COUNTRY_ITALY("Італія")    ;

    @NonNull
    private final String filterXpath;
}
