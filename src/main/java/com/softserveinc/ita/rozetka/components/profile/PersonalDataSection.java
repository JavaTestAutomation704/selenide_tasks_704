package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.models.PersonalData;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

@RequiredArgsConstructor
public class PersonalDataSection {

    private final String personalDataSectionXpath;
    private final String inputFirstNameXpath = "//input[@id='firstName']";
    private final String inputSecondNameXpath = "//input[@id='secondName']";
    private final String inputLastNameXpath = "//input[@id='lastName']";
    private final String inputBirthdayXpath = "//input[@id='birthday']";
    private final String errorMessageXpath = "/ancestor::li//p";


    @Step("Personal data section: start editing")
    public PersonalDataSection startEditing() {
        $x("//button[contains(@class,'personal-data__edit')]").click();
        return this;
    }

    @Step("Personal data section: fill in personal data {personalData}")
    public PersonalDataSection fillInPersonalData(PersonalData personalData) {
        $x(inputLastNameXpath).val(personalData.getLastName());
        $x(inputFirstNameXpath).val(personalData.getFirstName());
        $x(inputSecondNameXpath).val(personalData.getSecondName());
        $x(inputBirthdayXpath).val(personalData.getBirthday());
        return this;
    }

    public String getFirstNameErrorMessage() {
        return getText(inputFirstNameXpath + errorMessageXpath);
    }

    public String getSecondNameErrorMessage() {
        return getText(inputSecondNameXpath + errorMessageXpath);
    }

    public String getLastNameErrorMessage() {
        return getText(inputLastNameXpath + errorMessageXpath);
    }

    public String getBirthdayErrorMessage() {
        return getText(inputBirthdayXpath + errorMessageXpath);
    }

    public boolean isOpened() {
        return isVisible("//rz-cabinet-user-information");
    }

    public boolean isSaveButtonDisabled() {
        return hasAttribute(personalDataSectionXpath + "//button[@type='submit']", "disabled");
    }

    public boolean isFirstNameBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputFirstNameXpath, colorRgb);
    }

    public boolean isSecondNameBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputSecondNameXpath, colorRgb);
    }

    public boolean isLastNameBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputLastNameXpath, colorRgb);
    }

    public boolean isBirthdayBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputBirthdayXpath, colorRgb);
    }
}