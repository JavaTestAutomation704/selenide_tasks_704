package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Month {
    JANUARY("січня", "-01-"),
    FEBRUARY("лютого", "-02-"),
    MARCH("березня", "-03-"),
    APRIL("квітня", "-04-"),
    MAY("травня", "-05-"),
    JUNE("червня", "-06-"),
    JULY("липня", "-07-"),
    AUGUST("серпня", "-08-"),
    SEPTEMBER("вересня", "-09-"),
    OCTOBER("жовтня", "-10-"),
    NOVEMBER("листопада", "-11-"),
    DECEMBER("грудня", "-12-");

    @NonNull
    private final String month;
    private final String parseMonth;

    public static Month getByValue(String value) {
        for (Month month : values()) {
            if (month.month.equals(value)) {
                return month;
            }
        }
        throw new IllegalArgumentException("Can't get Month enum for value: " + value);
    }
}
