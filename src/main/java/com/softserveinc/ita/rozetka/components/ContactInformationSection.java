package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ContactInformationSection {

    private final String contactInformationSectionXpath = "//rz-checkout-contact-info";

    @Step("Contact information section: fill in contact information")
    public ContactInformationSection fillInContactInformation(String surname, String name, String phoneNumber) {
        $x(contactInformationSectionXpath + "//input[@formcontrolname = 'surname']").val(surname).pressEnter();
        $x(contactInformationSectionXpath + "//input[@formcontrolname = 'name']").val(name).pressEnter();
        $x(contactInformationSectionXpath + "//input[@id = 'checkoutUserPhone']").val(phoneNumber).pressEnter();
        return this;
    }
}
