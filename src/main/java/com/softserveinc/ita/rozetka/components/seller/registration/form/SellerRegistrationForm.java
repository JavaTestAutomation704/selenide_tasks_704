package com.softserveinc.ita.rozetka.components.seller.registration.form;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public abstract class SellerRegistrationForm {

    protected final String formFieldXpathTemplate = "//input[@id = 'mat-input-%d']";
    protected final String tabPanelXpathTemplate = "(//div[contains(@class,'mat-step-icon')])[%d]";

    protected boolean isOpened(String elementXpath) {
        return doesHaveCssValue(elementXpath, "transform", "none");
    }

    protected String getFieldErrorMessage(int fieldNumber) {
        var fieldErrorMessageXpathTemplate = String.format("(//mat-form-field)[%d]//mat-error", fieldNumber);
        if (isVisible(fieldErrorMessageXpathTemplate)) {
            return $x(fieldErrorMessageXpathTemplate).text();
        } else {
            return "Error message has not appeared";
        }
    }

    protected SellerRegistrationForm clearAllFields(List<Integer> fieldNumbers, int tabPanelNumber) {
        //It's the only way to get error messages for empty fields using selenium.Keys;
        fieldNumbers.forEach(fieldNumber ->
                $x(String.format(formFieldXpathTemplate, fieldNumber)).sendKeys("A", Keys.BACK_SPACE));
        $x(String.format(tabPanelXpathTemplate, tabPanelNumber)).click();
        return this;
    }
}
