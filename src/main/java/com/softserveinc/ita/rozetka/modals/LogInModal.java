package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class LogInModal {
    private final String logInButtonXpath = "//button[contains(@class, 'auth-modal__submit')]";
    private final String registerButtonXpath = "//button[contains(@class, 'auth-modal__register')]";

    @Step("Log In modal: open")
    public LogInModal open() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//rz-user-identification");
    }

    @Step("Log In modal: log in")
    public LogInModal logIn() {
        $x(logInButtonXpath).click();
        return this;
    }

    @Step("Registration modal: start registration")
    public RegistrationModal startRegistration() {
        $x(registerButtonXpath).click();
        return new RegistrationModal();
    }

    public boolean isLogInButtonVisible() {
        return isVisible(logInButtonXpath);
    }

    public boolean isRegistrationButtonVisible() {
        return isVisible(registerButtonXpath);
    }

    public String getEmailBorderColor(String color) {
        return getBorderColor("//input[@type='email']", color);
    }

    public String getPasswordBorderColor(String color) {
        return getBorderColor("//input[@type='password']", color);
    }

    public String getEmailErrorMessage() {
        return getText("//p[contains(@class, 'error-message')]");
    }
}