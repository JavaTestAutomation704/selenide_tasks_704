package com.softserveinc.ita.rozetka.data;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getLongFromInput;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitUntilUrlContains;

public class PriceFilter {

    private final String xpathMinPriceInput = "//input[@formcontrolname='min']";
    private final String xpathMaxPriceInput = "//input[@formcontrolname='max']";

    public long getMinPrice() {
        return getLongFromInput(xpathMinPriceInput);
    }

    public long getMaxPrice() {
        return getLongFromInput(xpathMaxPriceInput);
    }

    @Step("Price filter: set in minimum price field {price}")
    public SearchResultsPage setMinPrice(long price) {
        SelenideElement quantityInput = $x(xpathMinPriceInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(price));
        quantityInput.pressEnter();
        waitUntilUrlContains(String.format("price=%d", price));
        return new SearchResultsPage();
    }

    @Step("Price filter: set in maximum price field {price}")
    public SearchResultsPage setMaxPrice(long price) {
        SelenideElement quantityInput = $x(xpathMaxPriceInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(price));
        quantityInput.pressEnter();
        waitUntilUrlContains(String.format("-%d;", price));
        return new SearchResultsPage();
    }
}
