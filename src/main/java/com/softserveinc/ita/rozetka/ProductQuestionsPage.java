package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ProductQuestionItem;
import com.softserveinc.ita.rozetka.data.ProductQuestionsSort;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static java.lang.String.format;

public class ProductQuestionsPage {
    public ProductQuestionItem getProductQuestionItem(int number) {
        return new ProductQuestionItem(number);
    }

    @Step("Product questions page: sort questions by {sort}")
    public ProductQuestionsPage sort(ProductQuestionsSort sortCategory) {
        $x(format("//*[@id=\"sort-select\"]/option[%s]", sortCategory.getSortXpath())).click();
        return this;
    }

    public int getProductQuestionsQuantity() {
        return getCollectionSize("//li[contains(@class, 'product-questions__list')]");
    }

    public int getProductQuestionsWithPhotosQuantity() {
        return getCollectionSize("//li[contains(@class, 'product-comments__photos-item')]");
    }
}
