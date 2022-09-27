package com.softserveinc.ita.rozetka.utils;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.time.format.DateTimeFormatter.ofPattern;

public class PromotionDates {
    private final List<String> promotionPeriod;

    public PromotionDates(String elementXpath) {
        promotionPeriod = List.of(getText(elementXpath)
                .replaceAll("[^*]+:|.\\.", "")
                .trim()
                .split(" — "));
    }

    public LocalDate getStartPromotionDate() {
        return LocalDate.parse(promotionPeriod.get(0) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
    }

    public LocalDate getEndPromotionDate() {
        return LocalDate.parse(promotionPeriod.get(1) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
    }
}
