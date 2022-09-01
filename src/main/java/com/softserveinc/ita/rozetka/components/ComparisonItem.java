package com.softserveinc.ita.rozetka.components;

import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

@RequiredArgsConstructor
public class ComparisonItem {
    private final int itemNumber;

    public String getItemTitle() {
        return getText(String.format("(//rz-compare-tile//a)[%d]", itemNumber));
    }

    public long getItemPrice() {
        return Long.parseLong($x(String.format("(//rz-compare-tile//div[contains(@class, 'product__price ')])[%d]", itemNumber))
                .getOwnText()
                .replaceAll("[^0-9]", ""));
    }
}