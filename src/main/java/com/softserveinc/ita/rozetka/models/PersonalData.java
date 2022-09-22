package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonalData {
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final String birthday;
}