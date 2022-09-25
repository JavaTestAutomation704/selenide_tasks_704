package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ProductPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class PasswordEditingModal {
    private final String errorMessageXpath = "//p[contains(@class, 'error-message')]";
    private final String saveButtonXpathTemplate = "//button[@type='submit' and contains(@class,'navy')%s]";

    private void fillInPassword(String password, String inputFieldXpath) {
        var quantityInput = $x(inputFieldXpath);
        quantityInput.clear();
        quantityInput.sendKeys(password);
        quantityInput.pressTab();
    }

    @Step("Password editing modal: fill in current password {password}")
    public PasswordEditingModal fillInCurrentPassword(String password) {
        fillInPassword(password, "//input[@id='current_pass']");
        return this;
    }

    @Step("Password editing modal: fill in new password {password}")
    public PasswordEditingModal fillInNewPassword(String password) {
        fillInPassword(password, "//input[@id='new_pass']");
        return this;
    }

    @Step("Password editing modal: repeat fill in new password {password}")
    public PasswordEditingModal repeatFillInNewPassword(String password) {
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

    @Step("Password editing modal: save changes")
    public ProductPage saveChanges() {
        $x(format(saveButtonXpathTemplate, "")).click();
        return new ProductPage();
    }
}