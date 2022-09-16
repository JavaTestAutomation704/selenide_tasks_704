package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ContactsPage {

    @Step("Contacts page: open help center page")
    public HelpCenterPage openHelpCenterPage() {
        $x("//div[@class = 'contacts-help']/a[contains(@href, 'help.rozetka')]").click();
        return new HelpCenterPage();
    }

    public boolean isOpened() {
        return isVisible("//section[contains(@class, 'contacts-section')]/div[@class = 'contacts-main']");
    }
}
