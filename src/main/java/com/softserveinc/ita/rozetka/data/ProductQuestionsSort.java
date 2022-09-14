package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductQuestionsSort {
    BY_DATE("2"),
    BY_VOTE("3"),
    WITH_PHOTO("4");
    @NonNull
    private final String sortXpath;
}
