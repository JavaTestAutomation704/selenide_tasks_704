package com.softserveinc.ita.rozetka.components.seller.registration.form;

import com.softserveinc.ita.rozetka.models.Seller;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class StepOneSellerRegistrationForm extends SellerRegistrationForm {

    private final String shopNameFieldXpath = format(formFieldXpathTemplate, 0);
    private final String siteUrlFieldXpath = format(formFieldXpathTemplate, 1);
    private final String productsAmountFieldXpath = format(formFieldXpathTemplate, 2);

    public boolean isOpened() {
        return isOpened(format(tabPanelXpathTemplate, 1));
    }

    @Step("Step one seller registration form: open step two seller registration form tab panel")
    public StepTwoSellerRegistrationForm openStepTwoSellerRegistrationFormTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[3]").click();
        return new StepTwoSellerRegistrationForm();
    }

    @Step("Step two seller registration form: continue step two seller registration form")
    public StepTwoSellerRegistrationForm continueStepTwoSellerRegistrationForm() {
        $x(format(formButtonXpathTemplate, 1)).click();
        return new StepTwoSellerRegistrationForm();
    }

    @Step("Step one seller registration form: fill in shop information {seller}")
    public StepOneSellerRegistrationForm fillInShopInformation(Seller seller) {
        $x(shopNameFieldXpath).val(seller.getShopName()).click();
        $x(siteUrlFieldXpath).val(seller.getSiteUrl()).click();
        $x(productsAmountFieldXpath).val(seller.getProductsAmount()).click();
        $x(format(tabPanelXpathTemplate, 1)).click();
        return this;
    }

    @Step("Step one seller registration form: clear all fields")
    public StepOneSellerRegistrationForm clearAllFields() {
        clearAllFields(List.of(0, 1, 2), 1);
        return this;
    }

    public String getShopName() {
        return $x(shopNameFieldXpath).getValue();
    }

    public String getSiteUrl() {
        return $x(siteUrlFieldXpath).getValue();
    }

    public String getProductsAmount() {
        return $x(productsAmountFieldXpath).getValue();
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