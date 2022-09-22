package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.data.profile.CommunicationLanguage;
import com.softserveinc.ita.rozetka.data.profile.Gender;
import com.softserveinc.ita.rozetka.models.PersonalData;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.DateUtil.getFormattedDateFromString;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

@RequiredArgsConstructor
public class PersonalDataSection {

    private final String personalDataSectionXpath;

    @Step("Personal data section: start editing")
    public EditPersonalDataSection startEditing() {
        $x("//button[contains(@class,'personal-data__edit')]").click();
        return new EditPersonalDataSection(personalDataSectionXpath);
    }

    public PersonalData getPersonalData() {
        var dataXpathTemplate = "//label[@for='%s']/following-sibling::p";

        return PersonalData
                .builder()
                .lastName(getText(format(dataXpathTemplate, "lastName")))
                .firstName(getText(format(dataXpathTemplate, "firstName")))
                .secondName(getText(format(dataXpathTemplate, "secondName")))
                .birthday(getFormattedDateFromString(format(dataXpathTemplate, "birthday"), "dd-MM-yyyy"))
                .gender(Gender.getByValue(getText(format(dataXpathTemplate, "gender"))))
                .language(CommunicationLanguage.getByValue(getText(format(dataXpathTemplate, "rozetkaLanguage"))))
                .build();
    }

    public boolean isOpened() {
        return isVisible("//rz-cabinet-user-information");
    }
}