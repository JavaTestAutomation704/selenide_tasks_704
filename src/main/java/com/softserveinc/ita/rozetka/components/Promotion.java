package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.PromotionPage;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

public class Promotion {
    private final String promotionXpath;
    private List<String> promotionPeriod = null;

    public Promotion(int number) {
        this.promotionXpath = format("(//rz-promotion-tile)[%d]", number);
    }

    @Step("Promotion: open promotion page")
    public PromotionPage open() {
        $x(promotionXpath).click();
        return new PromotionPage();
    }

    public String getName() {
        return getText(promotionXpath + "//span[@class='promo-tile__heading']");
    }

    private List<String> getPromotionPeriod() {
        if (promotionPeriod == null) {
            promotionPeriod = List.of(getText(promotionXpath + "//time")
                    .replaceAll("[^*]+:|.\\.", "")
                    .trim()
                    .split(" — "));
        }
        return promotionPeriod;
    }

    public LocalDate getStartPromotionDate() {
        return LocalDate.parse(getPromotionPeriod().get(0) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
    }

    public LocalDate getEndPromotionDate() {
        return LocalDate.parse(getPromotionPeriod().get(1) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
    }
}