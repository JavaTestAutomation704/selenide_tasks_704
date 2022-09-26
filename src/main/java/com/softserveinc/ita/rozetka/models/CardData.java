package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardData {
    private final String cardNumber;
    private final String monthValidity;
    private final String yearValidity;
    private final String CardVerificationValue;
}