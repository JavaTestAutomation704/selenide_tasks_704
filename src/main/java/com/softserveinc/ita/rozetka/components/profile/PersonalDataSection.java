package com.softserveinc.ita.rozetka.components.profile;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PersonalDataSection {

    private final String personalDataSectionXpath;
    private final String saveButtonXpath = "//button[@type='submit']";
    private final String inputFirstNameXpath = "//input[@id='firstName']";
    private final String inputSecondNameXpath = "//input[@id='secondName']";
    private final String inputLastNameXpath = "//input[@id='lastName']";
    private final String inputBirthdayXpath = "//input[@id='birthday']";
    private final String errorMessageXpath = "/ancestor::li//p";

    public PersonalDataSection(String personalDataSectionXpath) {
        this.personalDataSectionXpath = personalDataSectionXpath;
    }


    @Step("Personal data section: edit")
    public PersonalDataSection edit() {
        $x("//button[contains(@class,'personal-data__edit')]").click();
        return this;
    }

    @Step("Personal data section: fill in personal data {lastName, firstName, secondName, birthday}")
    public PersonalDataSection fillInPersonalData(String lastName, String firstName,
                                                  String secondName, String birthday) {
        $x(inputLastNameXpath).val(lastName);
        $x(inputFirstNameXpath).val(firstName);
        $x(inputSecondNameXpath).val(secondName);
        $x(inputBirthdayXpath).val(birthday);
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
        return hasAttribute(personalDataSectionXpath + saveButtonXpath, "disabled");
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