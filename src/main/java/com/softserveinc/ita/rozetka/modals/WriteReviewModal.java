package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class WriteReviewModal {

    public boolean isModalOpen() {
        return isVisible("//rz-product-add-comments[@class='ng-star-inserted']");
    }

    @Step("Write review modal: write advantages {advantages}")
    public WriteReviewModal writeAdvantages(String advantages) {
        $x("//input[@id='dostoinstva']").setValue(advantages);
        return this;
    }

    @Step("Write review modal: write disadvantages {disadvantages}")
    public WriteReviewModal writeDisadvantages(String disadvantages) {
        $x("//input[@id='nedostatki']").setValue(disadvantages);
        return this;
    }

    @Step("Write review modal: write comments {comments}")
    public WriteReviewModal writeComment(String comment) {
        $x("//textarea[@id='comment-text']").setValue(comment);
        return this;
    }

    public List<String> getErrors() {
        return getElementsText("//p[contains(@class, 'validation-message')]");
    }

    @Step("Write review modal: start submitting a review")
    public WriteReviewModal startSubmitting() {
        $x("//button[@type='submit']").click();
        return this;
    }
}
