package com.softserveinc.ita.rozetka.components.seller.registration.form;

import com.softserveinc.ita.rozetka.model.Seller;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class StepTwoSellerRegistrationForm extends SellerRegistrationForm {

    private final String fullNameFieldXpath = String.format(formFieldXpathTemplate, 3);
    private final String positionFieldXpath = String.format(formFieldXpathTemplate, 4);
    private final String emailFieldXpath = String.format(formFieldXpathTemplate, 5);
    private final String phoneNumberFieldXpath = String.format(formFieldXpathTemplate, 6);

    public boolean isOpened() {
        return isOpened(format(tabPanelXpathTemplate, 3));
    }

    @Step("Step two seller registration form: open step one seller registration form via tab panel")
    public StepOneSellerRegistrationForm openStepOneSellerRegistrationFormViaTabPanel() {
        $x("(//div[contains(@class,'mat-step-icon')])[1]").click();
        return new StepOneSellerRegistrationForm();
    }

    @Step("Step one seller registration form: open step one seller registration form via back button")
    public StepOneSellerRegistrationForm openStepOneSellerRegistrationFormViaBackButton() {
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

    public boolean isContactInformationRemembered(Seller seller) {
        return Objects.equals($x(fullNameFieldXpath).getValue(), seller.getFullName()) &&
                Objects.equals($x(positionFieldXpath).getValue(), seller.getPosition()) &&
                Objects.equals($x(emailFieldXpath).getValue(), seller.getEmail()) &&
                Objects.equals($x(phoneNumberFieldXpath)
                        .getValue().replaceAll(" ", ""), seller.getPhoneNumber());
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
        return hasAttribute(String.format(formButtonXpathTemplate, 3),
                "disabled", "true");
    }
}