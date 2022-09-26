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

    @Step("Edit personal data section: fill in input personal data fields {personalData}")
    public EditPersonalDataSection fillInInputPersonalDataFields(PersonalData personalData) {
        $x(inputLastNameXpath).val(personalData.getLastName());
        $x(inputFirstNameXpath).val(personalData.getFirstName());
        $x(inputSecondNameXpath).val(personalData.getSecondName());
        $x(inputBirthdayXpath).val(personalData.getBirthday());
        return this;
    }

    @Step("Edit personal data section: fill in all personal data fields {personalData}")
    public EditPersonalDataSection fillInAllPersonalDataFields(PersonalData personalData) {
        fillInInputPersonalDataFields(personalData);
        $x("//select[@id='gender']").selectOption(personalData.getGender().getName());
        $x("//select[@id='rozetkaLanguage']").selectOption(personalData.getLanguage().getName());
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
        return isColorCorrect(inputFirstNameXpath, "border-color", colorRgb);
    }

    public boolean isSecondNameBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputSecondNameXpath, "border-color", colorRgb);
    }

    public boolean isLastNameBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputLastNameXpath, "border-color", colorRgb);
    }

    public boolean isBirthdayBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputBirthdayXpath, "border-color", colorRgb);
    }
}