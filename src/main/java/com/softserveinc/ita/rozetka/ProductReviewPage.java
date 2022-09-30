package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.components.ReviewItem;
import com.softserveinc.ita.rozetka.data.ReviewsSort;
import com.softserveinc.ita.rozetka.modals.FilterReviewsByRatingModal;
import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.modals.WriteReviewModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class ProductReviewPage {
    private final String reviewsXpath = "//div[@class='comment__inner']";

    public ReviewItem getReviewItem(int number) {
        return new ReviewItem(number);
    }

    @Step("Product review page: filter reviews by rating {rating}")
    public ProductReviewPage filterByRating(int rating) {
        var reviewsText = getText(reviewsXpath);
        $x("//button[contains(@class, 'product-comments__filters')]").click();
        $x("//div[contains(@class, 'modal__holder')]").shouldBe(Condition.visible);
        new FilterReviewsByRatingModal().filterByRating(rating);
        waitForTextChange(reviewsXpath, reviewsText);
        return this;
    }

    @Step("Product review page: sort reviews by {sort}")
    public ProductReviewPage sort(ReviewsSort sort) {
        var reviewsText = getText(reviewsXpath);
        $x(format("//select//option[@value='%s']", sort.getSortXpath())).click();
        waitForTextChange(reviewsXpath, reviewsText);
        return this;
    }

    public int getReviewsQuantity() {
        return getCollectionSize("//li[contains(@class, 'product-comments__list')]");
    }

    @Step("Product review page: start writing a review")
    public ProductReviewPage startWritingReview() {
        $x("//section[@class='product-comments__cta']//button").click();
        return this;
    }

    public boolean isLoginModalOpen() {
        return new LogInModal().isOpened();
    }

    @Step("Product review page: start writing review when user authorized")
    public WriteReviewModal startWritingReviewWhenUserAuthorized() {
        $x("//section[@class='product-comments__cta']//button").click();
        return new WriteReviewModal();
    }

    public String getTitle() {
        return getText("//h1").toLowerCase();
    }
}