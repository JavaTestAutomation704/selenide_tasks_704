package com.softserveinc.ita.rozetka.models;

import com.softserveinc.ita.rozetka.data.profile.CommunicationLanguage;
import com.softserveinc.ita.rozetka.data.profile.Gender;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonalData {
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final String birthday;
    private final Gender gender;
    private final CommunicationLanguage language;
}