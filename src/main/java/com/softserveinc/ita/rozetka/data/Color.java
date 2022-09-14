package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {

    GREEN("0, 160, 70", "Green"),
    RED("248, 65, 71", "Red");

    @NonNull
    private final String rgb;

    @NonNull
    private final String color;

    @Override
    public String toString() {
        return color;
    }
}