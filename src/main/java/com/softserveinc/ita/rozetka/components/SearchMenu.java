package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SearchMenu {

    private final String searchRequestXpathTemplate = "(//li/a/span[contains(text(), '%s')])";

    @Step("Header: clean search history")
    public Header clearSearchHistory() {
        $x("//button[contains(@class, 'search-suggest__heading-action')]")
                .click(ClickOptions.usingJavaScript());
        return new Header();
    }

    @Step("Search menu: clean search request {keyword}")
    public SearchMenu cleanSearchRequest(String keyword) {
        $x(String.format("//li[contains(@data-name, '%s')]/button", keyword)).click();
        return this;
    }

    public boolean isOpened() {
        return isVisible("//div[contains(@class, 'search-suggest ng-star-inserted')]");
    }

    public boolean isHistoryCleaned() {
        return !isOpened();
    }

    public boolean isRequestRemoved(String keyword) {
        return !isVisible(String.format(searchRequestXpathTemplate, keyword));
    }

    public boolean isLastSearched(String keyword) {
        return isVisible(String.format("%s[1]", keyword));
    }

    public boolean isPreviouslySearched(String keyword) {
        return isVisible(String.format("%s[2]", keyword));
    }
}
