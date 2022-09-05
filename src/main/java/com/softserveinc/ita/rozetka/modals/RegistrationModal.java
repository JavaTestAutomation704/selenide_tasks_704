package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class RegistrationModal {
    private final String registerButtonXpath = "//button[@class='button button--large button--green auth-modal__submit']";
    private final String errorMessageXpathTemplate = "(//p[@class='validation-message ng-star-inserted'])[%s]";

    @Step("Registration modal: open")
    public RegistrationModal open() {
        $x("//rz-user").click();
        $x("//button[@class='auth-modal__register-link button button--link ng-star-inserted']").click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//div[@class='modal__holder modal__holder_show_animation modal__holder--medium']");
    }

    @Step("Registration modal: register")
    public RegistrationModal register() {
        $x(registerButtonXpath).click();
        return this;
    }

    public boolean isFirstNameBorderColorCorrect(String color) {
        return isBorderColorCorrect("//input[@id='registerUserName']", color);
    }

    public boolean isLastNameBorderColorCorrect(String color) {
        return isBorderColorCorrect("//input[@id='registerUserSurname']", color);
    }

    public boolean isPhoneNumberBorderColorCorrect(String color) {
        return isBorderColorCorrect("//input[@id='registerUserPhone']", color);
    }

    public boolean isEmailBorderColorCorrect(String color) {
        return isBorderColorCorrect("//input[@id='registerUserEmail']", color);
    }

    public boolean isPasswordBorderColorCorrect(String color) {
        return isBorderColorCorrect("//input[@id='registerUserPassword']", color);
    }

    public String getFirstNameErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate, 1));
    }

    public String getLastNameErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate, 2));
    }

    public String getPhoneNumberErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate, 3));
    }

    public String getEmailErrorMessage() {
        return getText(String.format(errorMessageXpathTemplate, 4));
    }
}