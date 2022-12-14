package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.models.ContactInformation;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class ContactInformationSection {

    private final String contactInformationSectionXpath = "//rz-checkout-contact-info";
    private final String inputSurnameXpath = "//input[@formcontrolname = 'surname']";
    private final String inputNameXpath = "//input[@formcontrolname = 'name']";
    private final String inputPhoneXpath = "//input[@formcontrolname = 'phone']";

    @Step("Contact information section: fill in contact information {surname, name, phone}")
    public ContactInformationSection fillInContactInformation(String surname, String name, String phone) {
        fillInByChars(inputSurnameXpath, surname);
        fillInByChars(inputNameXpath, name);
        fillInByChars(inputPhoneXpath, phone);
        return this;
    }

    //TODO: this method solves the problem with copying of contact information to recipient contact information
    @Step("Contact information section: fill in by chars {inputXpath, inputValue}")
    private void fillInByChars(String inputXpath, String inputValue) {
        var recipientContactInformationSectionXpath = "//rz-checkout-order-recipient";
        for (var character : inputValue.split("")) {
            $x(contactInformationSectionXpath + inputXpath).click();
            $x(contactInformationSectionXpath + inputXpath).sendKeys(character);
            waitForAttributeValue(recipientContactInformationSectionXpath + inputXpath, "value", character);
        }
    }

    public boolean isNameBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputNameXpath, "border-color", colorRgb);
    }

    public boolean isSurnameBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputSurnameXpath, "border-color", colorRgb);
    }

    public boolean isPhoneNumberBorderColorCorrect(String colorRgb) {
        return isColorCorrect(inputPhoneXpath, "border-color", colorRgb);
    }

    public String getNameErrorMessage() {
        return getText("//div[contains(@class, 'js-name')]//p");
    }

    public String getSurnameErrorMessage() {
        return getText("//div[contains(@class, 'js-surname')]//p");
    }

    public String getPhoneNumberErrorMessage() {
        return getText("//div[contains(@class, 'js-phone')]//p");
    }

    public ContactInformation getContactInformation() {
        return ContactInformation
                .builder()
                .surname($x(contactInformationSectionXpath + inputSurnameXpath).val())
                .name($x(contactInformationSectionXpath + inputNameXpath).val())
                .phone($x(contactInformationSectionXpath + inputPhoneXpath).val())
                .build();
    }
}