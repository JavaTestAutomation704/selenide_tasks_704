package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ContactsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CompanyInformationSection {

    @Step("Company information section: open contacts page}")
    public ContactsPage openContactsPage() {
        $x("//a[contains(@href, '/contacts/')]").click();
        return new ContactsPage();
    }
}
