package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class LogInModal {
    private final String logInButtonXpath = "//button[contains(@class, 'auth-modal__submit')]";
    private final String registerButtonXpath = "//button[contains(@class, 'auth-modal__register')]";

    public LogInModal open() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isOpen() {
        return isVisible("//rz-user-identification");
    }

    public LogInModal logIn() {
        $x(logInButtonXpath).click();
        return this;
    }

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
