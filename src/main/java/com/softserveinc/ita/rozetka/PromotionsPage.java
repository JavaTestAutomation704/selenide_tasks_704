package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.ClickOptions;
import com.softserveinc.ita.rozetka.components.Promotion;
import com.softserveinc.ita.rozetka.data.PromotionFilter;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class PromotionsPage {

    public boolean isOpened() {
        return isVisible("//ul[contains(@class, 'promo-grid')]");
    }

    public Promotion getPromotion(int number) {
        return new Promotion(number);
    }

    public int getPromotionsQuantity() {
        var promotionXpath = "//rz-promotion-tile";
        waitTillVisible(promotionXpath);
        return getCollectionSize(promotionXpath);
    }

    @Step("Promotions page: filter promotion by filter {filter}")
    public PromotionsPage filter(PromotionFilter filter) {
        waitTillVisible("//aside[@spinnerid = 'LOAD_FILTERS']");
        $x(format("//a[@data-id = '%s']", filter.getFilterId()))
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript());
        waitTillPreloaderInvisible();
        return this;
    }
}