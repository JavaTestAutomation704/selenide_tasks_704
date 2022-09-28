package com.softserveinc.ita.rozetka.modals;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.HomePage;
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

    public boolean isOpened() {
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

    @Step("Log in modal: log in via facebook")
    public HomePage logInViaFacebook(String emailOrPhone, String password) {
        $x("//button[contains(text(),'Facebook')]").click();
        if (!isVisible("//a[@class='header__button ng-star-inserted']", 4)) {
            Selenide.switchTo().window(1);
            $x("//input[@id='email']").setValue(emailOrPhone);
            $x("//input[@id='pass']").setValue(password);
            $x("//input[@name='login']").click();
            Selenide.switchTo().window(0);
            waitInvisibility("//rz-single-modal-window");
        }
        return new HomePage();
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
        return isColorCorrect("//input[@type='email']", "border-color", colorRgb);
    }

    public boolean isPasswordBorderColorCorrect(String colorRgb) {
        return isColorCorrect("//input[@type='password']", "border-color", colorRgb);
    }

    public String getEmailErrorMessage() {
        return getText("//p[contains(@class, 'error-message')]");
    }
}