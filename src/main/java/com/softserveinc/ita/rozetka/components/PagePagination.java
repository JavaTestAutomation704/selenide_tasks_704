package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SubcategoryPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class PagePagination {

    public boolean isPreviousPageButtonEnable() {
        return isVisible("//a[contains(@class, 'direction ng') and contains(@href,'page')]");
    }

    public boolean isNextPageButtonEnable() {
        return isVisible("//a[contains(@class, 'direction pagination') and contains(@href,'page')]");
    }

    @Step("Page pagination: switch to page {number}")
    public SubcategoryPage switchToPage(long number) {
        $x(format("//ul[@class='pagination__list']//a[contains(text(),'%d')]", number))
                .scrollIntoView(false)
                .click();
        waitUntilUrlContains(format("page=%d", number));
        return new SubcategoryPage();
    }

    @Step("Page pagination: switch to page {number}")
    public SubcategoryPage loadMoreProducts() {
        $x("//span[@class='show-more__text']")
                .scrollIntoView(false)
                .click();
        waitTillPreloaderInvisible();
        return new SubcategoryPage();
    }

    public long getLastPageNumber() {
        var pageNumberButtonsQuantity = getCollectionSize("//ul[@class='pagination__list']/li");
        return getNumber(format("(//ul[@class='pagination__list']/li)[%d]", pageNumberButtonsQuantity));
    }
}
