package com.softserveinc.ita.rozetka.components.seller.registration.form;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class SellerRegistrationFormStepTwo extends SellerRegistrationForm {

    public boolean isOpened() {
        return isOpened(String.format(tabPanelXpathTemplate, 3));
    }

    public SellerRegistrationFormStepOne openSellerRegistrationFormStepOneViaTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[1]").click();
        return new SellerRegistrationFormStepOne();
    }

    public SellerRegistrationFormStepTwo fillInContactInformation(Seller seller) {
        $x(String.format(formFieldXpathTemplate, 3)).val(seller.getFullName()).click();
        $x(String.format(formFieldXpathTemplate, 4)).val(seller.getPosition()).click();
        $x(String.format(formFieldXpathTemplate, 5)).val(seller.getEmail()).click();
        $x(String.format(formFieldXpathTemplate, 6)).val(seller.getPhoneNumber()).click();
        $x(String.format(tabPanelXpathTemplate, 3)).click();
        return this;
    }

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
        return doesHaveAttribute("(//div[@class = 'registration-form--actions']/button)[3]",
                "disabled", "true");
    }
}