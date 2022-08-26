package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Availability {

    AVAILABLE("Є в наявності"),
    READY_TO_BE_DELIVERED("Готовий до відправлення"),
    RUNNING_OUT_OF_STOCK ("Закінчується"),
    OUT_OF_STOCK("Немає в наявності"),
    COMING_SOON("Очікується");

    @NonNull
    private final String filterValue;

    public static Availability getByValue(String value) {
        for (Availability availability : values()) {
            if (availability.filterValue.equals(value)) {
                return availability;
            }
        }
        return null;
    }
}