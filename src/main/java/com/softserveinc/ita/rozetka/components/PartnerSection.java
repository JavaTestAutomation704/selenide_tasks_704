package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.FranchisePage;
import com.softserveinc.ita.rozetka.SellOnRozetkaPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PartnerSection {

    @Step("Partner section: open sell on rozetka page")
    public SellOnRozetkaPage openSellOnRozetkaPage() {
        $x("//a[contains(@href, 'seller.rozetka')]").click();
        return new SellOnRozetkaPage();
    }

    @Step("Partner section: open franchise page")
    public FranchisePage openFranchisePage() {
        $x("//a[contains(@href, 'franchise')]").click();
        return new FranchisePage();
    }
}