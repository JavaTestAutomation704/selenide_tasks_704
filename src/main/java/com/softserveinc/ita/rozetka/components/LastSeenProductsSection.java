package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;

public class LastSeenProductsSection {

    @Step("Last seen products section: show more products")
    public LastSeenProductsSection showMoreProducts() {
        $x("(//section[contains(@class, 'main-goods')])[1]//button[contains(@class, 'main-goods__show-more')]")
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript());
        return this;
    }

    public int getProductsAmount() {
        waitTillVisible("(//rz-goods-section)[1]");
        return getCollectionSize("(//section[contains(@class, 'main-goods')])[1]//div[@class = 'tile']");
    }

    public boolean areMoreProductsShown(int primaryProductsAmount) {
        return getProductsAmount() > primaryProductsAmount;
    }
}