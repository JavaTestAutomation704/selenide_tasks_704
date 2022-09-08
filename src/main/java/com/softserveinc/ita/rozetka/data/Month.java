package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@Getter
@RequiredArgsConstructor
public enum Month {
    JANUARY("січня", Calendar.JANUARY),
    FEBRUARY("лютого", Calendar.FEBRUARY),
    MARCH("березня", Calendar.MARCH),
    APRIL("квітня", Calendar.APRIL),
    MAY("травня", Calendar.MAY),
    JUNE("червня", Calendar.JUNE),
    JULY("липня", Calendar.JULY),
    AUGUST("серпня", Calendar.AUGUST),
    SEPTEMBER("вересня", Calendar.SEPTEMBER),
    OCTOBER("жовтня", Calendar.OCTOBER),
    NOVEMBER("листопада", Calendar.NOVEMBER),
    DECEMBER("грудня", Calendar.DECEMBER);

    @NonNull
    private final String monthName;
    private final int monthNumber;

    public static int getNumberByValue(String value) {
        for (Month month : values()) {
            if (month.monthName.equals(value)) {
                return month.getMonthNumber();
            }
        }
        throw new IllegalArgumentException("Can't get Month enum for value: " + value);
    }
}
