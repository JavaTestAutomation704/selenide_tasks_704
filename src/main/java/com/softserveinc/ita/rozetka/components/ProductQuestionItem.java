package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.utils.DateUtil;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getNumber;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class ProductQuestionItem {
    private final String productQuestionItemXpath;

    public ProductQuestionItem(int number) {
        productQuestionItemXpath = format("((//li[contains(@class, 'product-questions__list')])[%s]//div[@class='comment__inner'])[1]", number);
        $x(productQuestionItemXpath).scrollIntoView(false);
    }

    public boolean hasPhoto() {
        return isVisible(productQuestionItemXpath + "//ul[@class='product-comments__photos-list ng-star-inserted']", 2);
    }

    public long getLikesQuantity() {
        return getNumber(productQuestionItemXpath + "//button[contains(@class,'product-comment__vote')][1]");
    }

    public long getDisLikesQuantity() {
        return getNumber(productQuestionItemXpath + "//button[contains(@class,'product-comment__vote')][2]");
    }

    public long getLikesAndDislikesDifference() {
        return getLikesQuantity() - getDisLikesQuantity();
    }

    public LocalDate getDate() {
        return DateUtil.getDate(productQuestionItemXpath + "//time");
    }
}