package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getLongFromInput;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitUntilUrlContains;

public class Filter extends Header {

    private final String xpathMinPriceInput = "//input[@formcontrolname='min']";
    private final String xpathMaxPriceInput = "//input[@formcontrolname='max']";

    @Step("Filter: select filter {type}")
    public SearchResultsPage filter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath()))
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript());
        waitTillVisible("//div[contains(@class, 'preloader') and contains(@hidden, '')]");
        return new SearchResultsPage();
    }

    @Step("Filter: select filters {types}")
    public SearchResultsPage filter(List<ProductFilter> types) {
        types.forEach(filter -> $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath()))
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript()));
        return new SearchResultsPage();
    }

    public long getMinPrice() {
        return getLongFromInput(xpathMinPriceInput);
    }

    public long getMaxPrice() {
        return getLongFromInput(xpathMaxPriceInput);
    }

    @Step("Filter: set in minimum price field {price}")
    public SearchResultsPage setMinPrice(long price) {
        var quantityInput = $x(xpathMinPriceInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(price));
        quantityInput.pressEnter();
        waitUntilUrlContains(String.format("price=%d", price));
        return new SearchResultsPage();
    }

    @Step("Filter: set in maximum price field {price}")
    public SearchResultsPage setMaxPrice(long price) {
        var quantityInput = $x(xpathMaxPriceInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(price));
        quantityInput.pressEnter();
        waitUntilUrlContains(String.format("-%d;", price));
        return new SearchResultsPage();
    }

    public boolean isSelected(ProductFilter type) {
        return isVisible(String.format("//a[@data-id = '%s'][contains(@class, 'link--checked')]",
                type.getFilterXpath()));
    }
}