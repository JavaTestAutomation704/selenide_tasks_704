package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ProductPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class PasswordChangeModal {

    private final String errorMessageXpath = "//p[contains(@class, 'error-message')]";
    private final String saveButtonXpathTemplate = "//button[@type='submit' and contains(@class,'navy')%s]";

    private void fillInPassword(String password, String inputFieldXpath) {
        var passwordInput = $x(inputFieldXpath);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordInput.pressTab();
    }

    @Step("Password change modal: fill in current password {password}")
    public PasswordChangeModal fillInCurrentPassword(String password) {
        fillInPassword(password, "//input[@id='current_pass']");
        return this;
    }

    @Step("Password change modal: fill in new password {password}")
    public PasswordChangeModal fillInNewPassword(String password) {
        fillInPassword(password, "//input[@id='new_pass']");
        return this;
    }

    @Step("Password change modal: repeat fill in new password {password}")
    public PasswordChangeModal repeatFillInNewPassword(String password) {
        fillInPassword(password, "//input[@id='repeated_new_pass']");
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessageXpath, 4);
    }

    public String getErrorMessageText() {
        return getText(errorMessageXpath);
    }

    public boolean isSaveButtonEnabled() {
        return !isVisible(format(saveButtonXpathTemplate, " and @disabled"), 4);
    }

    @Step("Password change modal: save changes")
    public ProductPage saveChanges() {
        $x(format(saveButtonXpathTemplate, "")).click();
        return new ProductPage();
    }
}