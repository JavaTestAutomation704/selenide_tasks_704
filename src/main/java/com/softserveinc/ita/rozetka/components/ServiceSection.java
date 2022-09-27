package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.GiftCertificatesPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ServiceSection {

    @Step("Service section: open gift certificates page")
    public GiftCertificatesPage openGiftCertificatesPage() {
        $x("//a[contains(@href, 'certificates')]").click();
        return new GiftCertificatesPage();
    }
}