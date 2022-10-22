package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ElectronicGiftCertificatesTransferSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class GiftCertificatesPage extends BasePage {

    public boolean isOpened() {
        return isVisible("//div[@class = 'rz-jobs__title rz-color__green']");
    }

    @Step("Gift certificates page: open electronic gift certificates transfer section")
    public ElectronicGiftCertificatesTransferSection openElectronicGiftCertificatesTransferSection() {
        $x("//div[contains(@class ,'rz-jobs__vacancy_item')][9]/a")
                //all actions below are needed as unexpected notification may appear
                .scrollTo()
                .hover()
                .pressEnter();
        return new ElectronicGiftCertificatesTransferSection();
    }
}