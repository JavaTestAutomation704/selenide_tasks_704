package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.ClickOptions;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getLongFromField;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitUntilUrlContains;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class Filter extends Header {

    private final String xpathMinPriceField = "//input[@formcontrolname='min']";
    private final String xpathMaxPriceField = "//input[@formcontrolname='max']";

    private void setPrice(long price, String elementXpath, String urlContent) {
        var quantityInput = $x(elementXpath);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(price));
        quantityInput.pressEnter();
        waitUntilUrlContains(String.format(urlContent, price));
    }

    @Step("Filter: select filter {type}")
    public SearchResultsPage filter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath()))
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript());
        var preloaderXpath = "//main[contains(@class, 'preloader_type_element')]";
        if (isVisible(preloaderXpath, 10)) {
            $x(preloaderXpath).shouldNotBe(visible, Duration.ofSeconds(10));
        }
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
        return getLongFromField(xpathMinPriceField);
    }

    public long getMaxPrice() {
        return getLongFromField(xpathMaxPriceField);
    }

    @Step("Filter: set in minimum price field {price}")
    public SearchResultsPage setMinPrice(long price) {
        setPrice(price, xpathMinPriceField, "price=%d");
        return new SearchResultsPage();
    }

    @Step("Filter: set in maximum price field {price}")
    public SearchResultsPage setMaxPrice(long price) {
        setPrice(price, xpathMaxPriceField, "-%d;");
        return new SearchResultsPage();
    }

    public boolean isSelected(ProductFilter type) {
        return isVisible(String.format("//a[@data-id = '%s'][contains(@class, 'link--checked')]",
                type.getFilterXpath()));
    }
}