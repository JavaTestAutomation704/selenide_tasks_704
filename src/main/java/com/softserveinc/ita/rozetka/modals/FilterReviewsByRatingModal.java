package com.softserveinc.ita.rozetka.modals;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class FilterReviewsByRatingModal {

    public FilterReviewsByRatingModal filterByRating(int rating) {
        $x(String.format("(//div[@class='ng-star-inserted']//label)[%s]", rating)).click();

        $x("//button[@type='submit']").click();
        $x("//div[contains(@class, 'modal__holder')]").shouldNotBe(Condition.visible);
        return this;
    }
}
