package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromotionFilter {
    PERMANENT_PROMOTION("Тільки діючі акції");

    @NonNull
    private final String filterXpath;
}
