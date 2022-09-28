package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.FranchiseFormField;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class FranchiseApplicationForm {

    private final String formFieldXpathTemplate = "//label[@for='%s']/parent::li//%s";
    private final String errorXpath = "div[contains(@class, 'error')]";

    @Step("Franchise application form: fill in '{field}' with '{text}'")
    public FranchiseApplicationForm fillIn(FranchiseFormField field, String text) {
        var formField = $x(format(formFieldXpathTemplate, field.getFieldName(), field.getFieldType()));
        formField.click();
        formField.sendKeys(text);
        return this;
    }

    @Step("Franchise application form: select '{cityNumber}' city")
    public FranchiseApplicationForm selectCity(int cityNumber) {
        $x(format("(//rz-autocomplete-option)[%d]", cityNumber)).click(usingJavaScript());
        return this;
    }

    public boolean isErrorVisible(FranchiseFormField field) {
        return isVisible(format(formFieldXpathTemplate, field.getFieldName(), errorXpath), 1);
    }

    public String getError(FranchiseFormField field) {
        return getText(format(formFieldXpathTemplate, field.getFieldName(), errorXpath));
    }

    @Step("Franchise application form: submit form")
    public FranchiseApplicationForm submit() {
        $x("//rz-widget-franchise//fieldset/button").click();
        return this;
    }

    public boolean isSubmitButtonEnabled() {
        return isVisible("//rz-widget-franchise//fieldset/button[not(@disabled='')]", 2);
    }
}