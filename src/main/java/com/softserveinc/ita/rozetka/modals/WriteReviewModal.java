package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class WriteReviewModal {

    public boolean isOpened() {
        return isVisible("//rz-product-add-comments[@class='ng-star-inserted']");
    }

    @Step("Write review modal: add pros {pros}")
    public WriteReviewModal addPros(String pros) {
        $x("//input[@id='dostoinstva']").setValue(pros);
        return this;
    }

    @Step("Write review modal: add cons {cons}")
    public WriteReviewModal addCons(String cons) {
        $x("//input[@id='nedostatki']").setValue(cons);
        return this;
    }

    @Step("Write review modal: add comment {comments}")
    public WriteReviewModal addComment(String comment) {
        $x("//textarea[@id='comment-text']").setValue(comment);
        return this;
    }

    public List<String> getErrors() {
        return getElementsText("//p[contains(@class, 'validation-message')]");
    }

    @Step("Write review modal: submit a review")
    public WriteReviewModal submit() {
        $x("//button[@type='submit']").click();
        return this;
    }
}
