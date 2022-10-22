package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EditOrderRecipientField {

    FIRST_NAME("firstName", "first name"),
    LAST_NAME("lastName", "last name"),
    SECOND_NAME("secondName", "second name"),
    PHONE("phoneTitle", "phone"),
    PROFILE_NAME("alias", "profile name");

    @NonNull
    private final String fieldId;
    @NonNull
    private final String fieldName;

    @Override
    public String toString() {
        return getFieldName();
    }
}