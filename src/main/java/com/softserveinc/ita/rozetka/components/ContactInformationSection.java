package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ContactInformationSection {

    @Step("Contact information section: fill in contact information")
    public ContactInformationSection fillInContactInformation(String surname, String name, String phoneNumber) {
        $x("//rz-checkout-contact-info//input[@formcontrolname = 'surname']").val(surname).pressEnter();
        $x("//rz-checkout-contact-info//input[@formcontrolname = 'name']").val(name).pressEnter();
        $x("//rz-checkout-contact-info//input[@id = 'checkoutUserPhone']").val(phoneNumber).pressEnter();
        return this;
    }
}