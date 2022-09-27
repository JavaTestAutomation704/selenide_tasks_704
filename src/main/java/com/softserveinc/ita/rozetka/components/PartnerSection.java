package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.FranchisePage;
import com.softserveinc.ita.rozetka.SellOnRozetkaPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class PartnerSection {

    @Step("Partner section: open sell on rozetka page")
    public SellOnRozetkaPage openSellOnRozetkaPage() {
        $x("//a[contains(@href, 'seller.rozetka')]").click();
        return new SellOnRozetkaPage();
    }

    @Step("Partner section: open franchise page")
    public FranchisePage openFranchisePage() {
        $x("//a[contains(@href, 'franchise')]").click();
        // remove annoying promotional video
        if (isVisible("//div[@class='rz-video rz_section']")) {
            executeJavaScript("document.getElementById(\"youtubeiframe\").remove();");
        }
        return new FranchisePage();
    }
}