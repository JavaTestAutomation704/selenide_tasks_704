package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.models.PersonalData;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

@RequiredArgsConstructor
public class EditPersonalDataSection {

    private final String personalDataSectionXpath;
    private final String saveButtonXpath = "//button[@type='submit']";
    private final String inputFirstNameXpath = "//input[@id='firstName']";
    private final String inputSecondNameXpath = "//input[@id='secondName']";
    private final String inputLastNameXpath = "//input[@id='lastName']";
    private final String inputBirthdayXpath = "//input[@id='birthday']";
    private final String errorMessageXpath = "/ancestor::li//p";


    @Step("Edit personal data section: save")
    public PersonalDataSection save() {
        $x(personalDataSectionXpath + saveButtonXpath).click();
        return new PersonalDataSection(personalDataSectionXpath);
    }

    @Step("Edit personal data section: fill in personal data {personalData}")
    public EditPersonalDataSection fillInPersonalData(PersonalData personalData) {
        $x(inputLastNameXpath).val(personalData.getLastName());
        $x(inputFirstNameXpath).val(personalData.getFirstName());
        $x(inputSecondNameXpath).val(personalData.getSecondName());
        $x(inputBirthdayXpath).val(personalData.getBirthday());
        return this;
    }

    @Step("Edit personal data section: fill in personal data including selectors {personalData}")
    public EditPersonalDataSection fillInPersonalDataIncludingSelectors(PersonalData personalData) {
        fillInPersonalData(personalData);
        $x("//select[@id='gender']").selectOption(personalData.getGender());
        $x("//select[@id='rozetkaLanguage']").selectOption(personalData.getLanguage());
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