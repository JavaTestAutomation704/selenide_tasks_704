package com.softserveinc.ita.rozetka.utils;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Locale;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.time.format.DateTimeFormatter.ofPattern;

@Getter
public class PromotionPeriod {
    private final LocalDate startPromotionDate;
    private final LocalDate endPromotionDate;

    public PromotionPeriod(String elementXpath) {
        Locale.setDefault(Locale.forLanguageTag("uk-UA"));
        var promotionPeriod = List.of(getText(elementXpath)
                .replaceAll("[^*]+:|.\\.", "")
                .trim()
                .split(" — "));

        startPromotionDate = LocalDate.parse(promotionPeriod.get(0) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
        endPromotionDate = LocalDate.parse(promotionPeriod.get(1) + " " + Year.now().getValue(), ofPattern("d MMMM yyyy"));
    }
}
