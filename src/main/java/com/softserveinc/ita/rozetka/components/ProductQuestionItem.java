package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ProductQuestionItem {
    private final String productQuestionItemXpath;

    public ProductQuestionItem(int number) {
        productQuestionItemXpath = String.format("((//li[contains(@class, 'product-questions__list')])[%s]//div[@class='comment__inner'])[1]", number);
        $x(productQuestionItemXpath).scrollIntoView(false);
    }

    public boolean hasPhoto() {
        return isVisible(productQuestionItemXpath + "//ul[@class='product-comments__photos-list ng-star-inserted']", 2);
    }
}