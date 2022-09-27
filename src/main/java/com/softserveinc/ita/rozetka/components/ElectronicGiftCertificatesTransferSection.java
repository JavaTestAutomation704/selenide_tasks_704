package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.GiftCertificateTransferPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ElectronicGiftCertificatesTransferSection {

    public boolean isOpened() {
        return isVisible("//div[contains(@class ,'rz-jobs__vacancy_item')][9]" +
                "/div[@class = 'rz-jobs__vacancy_description']");
    }

    @Step("Electronic gift certificates transfer section: open gift certificates transfer page")
    public GiftCertificateTransferPage openGiftCertificatesTransferPage() {
        $x("(//a[contains(@href, '/gift/')])[2]").click();
        return new GiftCertificateTransferPage();
    }
}