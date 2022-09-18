package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class BonusAccountPage {
    public boolean isBannerVisible(){
        return isVisible("//div[contains(@class, 'rz-video')]");
    }

    @Step("Bonus account page: open product with bonuses page")
    public ProductWithBonusesCategoryPage openProductWithBonusesCategoryPage() {
        $x("(//a[contains(@class, 'button_color_green')])[3]")
                .scrollIntoView(false)
                .click();
        return new ProductWithBonusesCategoryPage();
    }
}
