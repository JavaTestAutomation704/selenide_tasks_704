package com.softserveinc.ita.rozetka;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ProductPage extends BasePage {
    public ProductPage buyOnCredit() {
        $x("//app-product-credit").click();
        return this;
    }

    public boolean isCreditModalVisible() {
        return isVisible("//credit-modal");
    }
}