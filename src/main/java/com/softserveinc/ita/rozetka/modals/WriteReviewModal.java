package com.softserveinc.ita.rozetka.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class WriteReviewModal {
    private final String errorsXpath = "//p[contains(@class, 'validation-message')]";

    public boolean isModalOpen() {
        return isVisible("//rz-product-add-comments[@class='ng-star-inserted']");
    }

    private void write(String id, String text) {
        $(By.id(id)).setValue(text);
    }

    @Step("Write review modal: write advantages {advantages}")
    public WriteReviewModal writeAdvantages(String advantages) {
        write("dostoinstva", advantages);
        return this;
    }

    @Step("Write review modal: write disadvantages {disadvantages}")
    public WriteReviewModal writeDisadvantages(String disadvantages) {
        write("nedostatki", disadvantages);
        return this;
    }

    @Step("Write review modal: write comments {comments}")
    public WriteReviewModal writeComment(String comment) {
        write("comment-text", comment);
        return this;
    }

    public int getErrorsQuantity() {
        return getCollectionSize(errorsXpath);
    }

    public List<String> getErrors() {
        return getElementsText(errorsXpath);
    }

    @Step("Write review modal: start submitting a review")
    public WriteReviewModal startSubmitting() {
        $x("//button[@type='submit']").click();
        return this;
    }
}
