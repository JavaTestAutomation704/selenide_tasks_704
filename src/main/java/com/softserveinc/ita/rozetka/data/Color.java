package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {

    GREEN("0, 160, 70"),
    RED("248, 65, 71");

    @NonNull
    private final String rgb;
}