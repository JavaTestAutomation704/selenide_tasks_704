package com.softserveinc.ita.rozetka.components.seller.registration.form;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class SellerRegistrationFormStepTwo extends SellerRegistrationForm {

    public boolean isOpened() {
        return isOpened(format(tabPanelXpathTemplate, 3));
    }

    @Step("Seller registration form step two: open seller registration form step one via tab panel")
    public SellerRegistrationFormStepOne openSellerRegistrationFormStepOneViaTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[1]").click();
        return new SellerRegistrationFormStepOne();
    }

    @Step("Seller registration form step two: fill in contact information {seller}")
    public SellerRegistrationFormStepTwo fillInContactInformation(Seller seller) {
        $x(format(formFieldXpathTemplate, 3)).val(seller.getFullName()).click();
        $x(format(formFieldXpathTemplate, 4)).val(seller.getPosition()).click();
        $x(format(formFieldXpathTemplate, 5)).val(seller.getEmail()).click();
        $x(format(formFieldXpathTemplate, 6)).val(seller.getPhoneNumber()).click();
        $x(format(tabPanelXpathTemplate, 3)).click();
        return this;
    }

    @Step("Seller registration form step two: clear all fields")
    public SellerRegistrationFormStepTwo clearAllFields() {
        clearAllFields(List.of(3, 4, 5, 6), 3);
        return this;
    }

    public String getFullNameFieldErrorMessage() {
        return getFieldErrorMessage(5);
    }

    public String getPositionFieldErrorMessage() {
        return getFieldErrorMessage(6);
    }

    public String getEmailFieldErrorMessage() {
        return getFieldErrorMessage(7);
    }

    public String getPhoneNumberFieldErrorMessage() {
        return getFieldErrorMessage(8);
    }

    public boolean isRegistrationButtonDisabled() {
        return hasAttribute("(//div[@class = 'registration-form--actions']/button)[3]",
                "disabled", "true");
    }
}