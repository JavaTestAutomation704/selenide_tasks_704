package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.models.PersonalData;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getFormattedDateFromString;
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
                .lastName($x(format(dataXpathTemplate, "lastName")).text())
                .firstName($x(format(dataXpathTemplate, "firstName")).text())
                .secondName($x(format(dataXpathTemplate, "secondName")).text())
                .birthday(getFormattedDateFromString(format(dataXpathTemplate, "birthday"), "dd-MM-yyyy"))
                .gender($x(format(dataXpathTemplate, "gender")).text())
                .language($x(format(dataXpathTemplate, "rozetkaLanguage")).text())
                .build();
    }

    public boolean isOpened() {
        return isVisible("//rz-cabinet-user-information");
    }
}