package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class WishlistItem {
    private final String itemXpath;
    private final String itemTitleXpath;

    public WishlistItem(String wishlistXpath, int itemNumber) {
        this.itemXpath = format("(%s//app-goods-tile)[%d]", wishlistXpath, itemNumber);
        this.itemTitleXpath = itemXpath + "//span[contains(@class, 'title')]";
    }

    public String getTitle() {
        return getText(itemTitleXpath);
    }

    @Step("Wishlist item: select item")
    public WishlistItem select() {
        $x(itemTitleXpath).scrollIntoView(false);
        $x(itemXpath + "//div[contains(@class, 'tile-checkbox')]").click();
        return this;
    }
}