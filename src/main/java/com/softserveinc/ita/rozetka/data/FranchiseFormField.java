package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FranchiseFormField {

    NAME("name", "input"),
    PHONE("phone", "input"),
    EMAIL("email", "input"),
    CITY("city", "input"),
    EXPERIENCE("experiance", "textarea"), //typo in Rozetka source code
    MOTIVATION("motivation", "textarea"),
    YOUTUBE("youtube", "input");

    @NonNull
    private final String fieldName;
    @NonNull
    private final String fieldType;
}