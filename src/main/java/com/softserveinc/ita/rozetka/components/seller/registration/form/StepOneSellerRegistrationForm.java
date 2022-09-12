package com.softserveinc.ita.rozetka.components.seller.registration.form;

import com.softserveinc.ita.rozetka.model.Seller;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class StepOneSellerRegistrationForm extends SellerRegistrationForm {

    private final String shopNameFieldXpath = String.format(formFieldXpathTemplate, 0);
    private final String siteUrlFieldXpath = String.format(formFieldXpathTemplate, 1);
    private final String productsAmountFieldXpath = String.format(formFieldXpathTemplate, 2);

    public boolean isOpened() {
        return isOpened(format(tabPanelXpathTemplate, 1));
    }

    @Step("Step one seller registration form: open step two seller registration form via tab panel")
    public StepTwoSellerRegistrationForm openStepTwoSellerRegistrationFormViaTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[3]").click();
        return new StepTwoSellerRegistrationForm();
    }

    @Step("Step two seller registration form: open step two seller registration form via continue button")
    public StepTwoSellerRegistrationForm openStepTwoSellerRegistrationFormViaContinueButton() {
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

    public boolean isShopInformationRemembered(Seller seller) {
        return Objects.equals($x(shopNameFieldXpath).getValue(), seller.getShopName()) &&
                Objects.equals($x(siteUrlFieldXpath).getValue(), seller.getSiteUrl()) &&
                Objects.equals($x(productsAmountFieldXpath).getValue(), seller.getProductsAmount());
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