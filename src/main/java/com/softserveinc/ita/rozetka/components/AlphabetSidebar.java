package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class AlphabetSidebar {
    private final String alphabetSidebarXpath = "[contains(@class, 'sidebar-alphabet')]";

    @Step("Alphabet sidebar: open")
    public AlphabetSidebar open() {
        $x("//button" + alphabetSidebarXpath).click();
        return this;
    }

    public boolean isOpened() {
        return isVisible("//ul" + alphabetSidebarXpath);
    }

    @Step("Alphabet sidebar: search by letter {letter}")
    public SearchResultsPage searchByLetter(String letter) {
        var brandSearchResultsXpath = "//div[@data-filter-name='producer']//rz-scrollbar//a";
        int brandSearchResultsQuantity = getCollectionSize(brandSearchResultsXpath);

        $x(format("//a[contains(@class, 'sidebar-alphabet') and text()=' %s ']", letter)).click();
        waitTillPreloaderInvisible();
        waitCollectionSizeChange(brandSearchResultsXpath, brandSearchResultsQuantity);

        return new SearchResultsPage();
    }
}