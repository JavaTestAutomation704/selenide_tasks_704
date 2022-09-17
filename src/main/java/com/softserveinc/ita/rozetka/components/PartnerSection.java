package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.FranchisePage;
import com.softserveinc.ita.rozetka.SellOnRozetkaPage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.*;

public class PartnerSection {

    @Step("Partner section: open sell on rozetka page")
    public SellOnRozetkaPage openSellOnRozetkaPage() {
        $x("//a[contains(@href, 'seller.rozetka')]").click();
        return new SellOnRozetkaPage();
    }

    @Step("Partner section: open franchise page")
    public FranchisePage openFranchisePage() {
        $x("//a[contains(@href, 'franchise')]").click();

        $x("//div[@class='rz-video rz_section']").scrollIntoView(false);
        ((JavascriptExecutor) Selenide.webdriver().driver().getWebDriver())
                .executeScript("document.getElementById(\"youtubeiframe\").remove();");

        return new FranchisePage();
    }
}