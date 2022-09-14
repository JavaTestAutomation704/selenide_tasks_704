package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getNumber;

public class CheaperTogetherSection {
    public long getMainProductPrice() {
        return getNumber($x("(//p[@class='kits-tile__price'])[1]"));
    }

    public long getAdditionProductOldPrice() {
        return getNumber($x("(//p[contains(@class,'kits-tile__price_type_old')])[1]"));
    }

    public long getAdditionProductActualPrice() {
        return getNumber($x("(//p[contains(@class,'kits-tile__price_color_red')])[1]"));
    }

    public long totalPrice() {
        return getNumber($x("(//div[@class='kits-price__coast'])[1]"));

    }

    @Step("Cheaper together section: open addition product page")
    public ProductPage openAdditionProductPage() {
        $x("(//a[@class='kits-tile__picture'])[2]").click();
        return new ProductPage();
    }
}
