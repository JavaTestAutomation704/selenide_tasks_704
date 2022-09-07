package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewsSort {
    BY_DATE("date"),
    WITH_PHOTO("with_attachments");
    @NonNull
    private final String sortXpath;
}
