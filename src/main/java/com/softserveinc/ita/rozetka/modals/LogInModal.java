package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class LogInModal {
    private final String logInButtonXpath = "//button[contains(@class, 'auth-modal__submit')]";
    private final String registerButtonXpath = "//button[contains(@class, 'auth-modal__register')]";
    private final String remindPasswordButtonXpath = "//a[contains(@class,'restore-link')]";
    private final String getTemporaryPasswordButtonXpath = "//button[contains(@class,'get-pass')]";
    private final String rememberPasswordButtonXpath = "//a[contains(@class,'remember-link')]";

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

    @Step("Log In modal: start registration")
    public RegistrationModal startRegistration() {
        $x(registerButtonXpath).click();
        return new RegistrationModal();
    }

    @Step("Log In modal: remind password")
    public LogInModal remindPassword() {
        $x(remindPasswordButtonXpath).click();
        return this;
    }

    @Step("Log In modal: get temporary password")
    public LogInModal getTemporaryPassword() {
        $x(getTemporaryPasswordButtonXpath).click();
        return this;
    }

    @Step("Log In modal: remember password")
    public LogInModal rememberPassword() {
        $x(rememberPasswordButtonXpath).click();
        return this;
    }

    public boolean isLogInButtonVisible() {
        return isVisible(logInButtonXpath);
    }

    public boolean isRegistrationButtonVisible() {
        return isVisible(registerButtonXpath);
    }

    public boolean isRemindPasswordButtonVisible() {
        return isVisible(remindPasswordButtonXpath);
    }

    public boolean isGetTemporaryPasswordButtonVisible() {
        return isVisible(getTemporaryPasswordButtonXpath);
    }

    public boolean isRememberPasswordButtonVisible() {
        return isVisible(rememberPasswordButtonXpath);
    }

    public boolean isEmailBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect("//input[@type='email']", colorRgb);
    }

    public boolean isPasswordBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect("//input[@type='password']", colorRgb);
    }

    public String getEmailErrorMessage() {
        return getText("//p[contains(@class, 'error-message')]");
    }
}