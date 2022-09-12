package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContactInformation {

    private final String surname;
    private final String name;
    private final String phone;
}