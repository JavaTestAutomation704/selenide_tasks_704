package com.softserveinc.ita.rozetka.components.seller.registration.form;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SellerRegistrationFormStepOne extends SellerRegistrationForm {

    public boolean isOpened() {
        return isOpened(String.format(tabPanelXpathTemplate, 1));
    }

    @Step("Seller registration form step one: open seller registration form step two via tab panel")
    public SellerRegistrationFormStepTwo openSellerRegistrationFormStepTwoViaTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[3]").click();
        return new SellerRegistrationFormStepTwo();
    }

    @Step("Seller registration form step one: fill in shop information {seller}")
    public SellerRegistrationFormStepOne fillInShopInformation(Seller seller) {
        $x(String.format(formFieldXpathTemplate, 0)).val(seller.getShopName()).click();
        $x(String.format(formFieldXpathTemplate, 1)).val(seller.getSiteUrl()).click();
        $x(String.format(formFieldXpathTemplate, 2)).val(seller.getProductsAmount()).click();
        $x(String.format(tabPanelXpathTemplate, 1)).click();
        return this;
    }

    @Step("Seller registration form step one: clear all fields")
    public SellerRegistrationFormStepOne clearAllFields() {
        clearAllFields(List.of(0, 1, 2), 1);
        return this;
    }

    public String getShopNameFieldErrorMessage() {
        return getFieldErrorMessage(1);
    }

    public String getSiteUrlFieldErrorMessage() {
        return getFieldErrorMessage(3);
    }

    public String getProductsAmountFieldErrorMessage() {
        return getFieldErrorMessage(4);
    }
}