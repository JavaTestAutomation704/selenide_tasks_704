package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class RegistrationModal {
    private final String registerButtonXpath = "//button[@class='button button--large button--green auth-modal__submit']";
    private final String errorMessageXpathTemplate = "(//p[@class='validation-message ng-star-inserted'])[%s]";

    public RegistrationModal open() {
        $x("//rz-user").click();
        $x("//button[@class='auth-modal__register-link button button--link ng-star-inserted']").click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//div[@class='modal__holder modal__holder_show_animation modal__holder--medium']");
    }

    public RegistrationModal register() {
        $x(registerButtonXpath).click();
        return this;
    }

    public String getFirstNameBorderColor(String color) {
        return getBorderColor("//input[@id='registerUserName']", color);
    }

    public String getLastNameBorderColor(String color) {
        return getBorderColor("//input[@id='registerUserSurname']", color);
    }

    public String getPhoneNumberBorderColor(String color) {
        return getBorderColor("//input[@id='registerUserPhone']", color);
    }

    public String getEmailBorderColor(String color) {
        return getBorderColor("//input[@id='registerUserEmail']", color);
    }

    public String getPasswordBorderColor(String color) {
        return getBorderColor("//input[@id='registerUserPassword']", color);
    }

    public String getFirstNameErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate,1));
    }

    public String getLastNameErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate,2));
    }

    public String getPhoneNumberErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate,3));
    }

    public String getEmailErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate,4));
    }
}