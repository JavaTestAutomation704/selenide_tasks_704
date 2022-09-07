package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isBorderColorCorrect;

public class ContactInformationSection {
    private final String inputSurnameXpath = "//rz-checkout-contact-info//input[@formcontrolname = 'surname']";
    private final String inputNameXpath = "//rz-checkout-contact-info//input[@formcontrolname = 'name']";
    private final String inputPhoneXpath = "//rz-checkout-contact-info//input[@formcontrolname = 'phone']";

    @Step("Contact information section: fill in contact information")
    public ContactInformationSection fillInContactInformation(String surname, String name, String phoneNumber) {
        $x(inputSurnameXpath).val(surname).pressEnter();
        $x(inputNameXpath).val(name).pressEnter();
        $x(inputPhoneXpath).val(phoneNumber).pressEnter();
        return this;
    }

    public boolean isNameBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputNameXpath, colorRgb);
    }

    public boolean isSurnameBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputSurnameXpath, colorRgb);
    }

    public boolean isPhoneNumberBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputPhoneXpath, colorRgb);
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

    public List<String> getContactInformation() {
        return List.of($x(inputSurnameXpath).text(),
                $x(inputNameXpath).text(),
                $x(inputPhoneXpath).text());
    }
}