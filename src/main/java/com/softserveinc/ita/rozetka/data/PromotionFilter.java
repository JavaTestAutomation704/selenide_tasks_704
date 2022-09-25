package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromotionFilter {
    WITH_GIFTS("З подарунками"),
    NOVELTY("Новинка");

    @NonNull
    private final String filterXpath;
}
