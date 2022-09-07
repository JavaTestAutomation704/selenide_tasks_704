package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.components.ReviewItem;
import com.softserveinc.ita.rozetka.data.ReviewsSort;
import com.softserveinc.ita.rozetka.modals.FilterReviewsByRatingModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;

public class ProductReviewPage {
    public ReviewItem getReviewItem(int number) {
        return new ReviewItem(number);
    }

    @Step("Product review page: filter reviews by rating {rating}")
    public ProductReviewPage filterByRating(int rating) {
        $x("//button[contains(@class, 'product-comments__filters')]").click();
        $x("//div[contains(@class, 'modal__holder')]").shouldBe(Condition.visible);
        new FilterReviewsByRatingModal().filterByRating(rating);
        return this;
    }

    @Step("Product review page: sort reviews by {sort}")
    public ProductReviewPage sort(ReviewsSort sort) {
        $x(String.format("//select//option[@value='%s']", sort.getSortXpath())).click();
        return this;
    }

    public int getReviewsQuantity() {
        return getCollectionSize("//li[contains(@class, 'product-comments__list')]");
    }
}
