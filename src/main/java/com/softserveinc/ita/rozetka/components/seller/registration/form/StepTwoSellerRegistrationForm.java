package com.softserveinc.ita.rozetka.components.seller.registration.form;

import com.softserveinc.ita.rozetka.models.Seller;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.hasAttributeValue;
import static java.lang.String.format;

public class StepTwoSellerRegistrationForm extends SellerRegistrationForm {

    private final String fullNameFieldXpath = format(formFieldXpathTemplate, 3);
    private final String positionFieldXpath = format(formFieldXpathTemplate, 4);
    private final String emailFieldXpath = format(formFieldXpathTemplate, 5);
    private final String phoneNumberFieldXpath = format(formFieldXpathTemplate, 6);

    public boolean isOpened() {
        return isOpened(format(tabPanelXpathTemplate, 3));
    }

    @Step("Step two seller registration form: open step one seller registration form")
    public StepOneSellerRegistrationForm openStepOneSellerRegistrationForm() {
        $x("(//div[contains(@class,'mat-step-icon')])[1]").click();
        return new StepOneSellerRegistrationForm();
    }

    @Step("Step one seller registration form: back to step one seller registration form")
    public StepOneSellerRegistrationForm backToStepOneSellerRegistrationForm() {
        $x(format(formButtonXpathTemplate, 2)).click();
        return new StepOneSellerRegistrationForm();
    }

    @Step("Step two seller registration form: fill in contact information {seller}")
    public StepTwoSellerRegistrationForm fillInContactInformation(Seller seller) {
        $x(fullNameFieldXpath).val(seller.getFullName()).click();
        $x(positionFieldXpath).val(seller.getPosition()).click();
        $x(emailFieldXpath).val(seller.getEmail()).click();
        $x(phoneNumberFieldXpath).val(seller.getPhoneNumber()).click();
        $x(format(tabPanelXpathTemplate, 3)).click();
        return this;
    }

    @Step("Step two seller registration form: clear all fields")
    public StepTwoSellerRegistrationForm clearAllFields() {
        clearAllFields(List.of(3, 4, 5, 6), 3);
        return this;
    }

    public String getFullName() {
        return $x(fullNameFieldXpath).getValue();
    }

    public String getPosition() {
        return $x(positionFieldXpath).getValue();
    }

    public String getEmail() {
        return $x(emailFieldXpath).getValue();
    }

    public String getPhoneNumber() {
        return $x(phoneNumberFieldXpath).getValue().replaceAll(" ", "");
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
        return hasAttributeValue(format(formButtonXpathTemplate, 3), "disabled", "true");
    }
}