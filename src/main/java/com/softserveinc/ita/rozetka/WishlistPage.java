package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.WishlistItem;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class WishlistPage extends BasePage {

    @Step("Wishlist page: select all items")
    public WishlistPage selectAllItems() {
        $x("//rz-cabinet-wishlist-details//button[contains(@class, 'check-all')]")
                .scrollIntoView(false)
                .click();
        return this;
    }

    @Step("Wishlist page: remove selected items")
    public WishlistPage removeSelectedItems() {
        var wishlistCounterXpath = "//rz-wishlist//rz-icon-counter";
        var wishlistCounterQuantity = getText(wishlistCounterXpath);
        $x("//div[contains(@class, 'wish-details__actions')]//button[contains(@class, 'delete')]")
                .scrollIntoView(false)
                .click();
        if (isVisible(wishlistCounterXpath, 2)) {
            waitForTextChange(wishlistCounterXpath, wishlistCounterQuantity);
        }
        return this;
    }

    public int getWishlistItemsQuantity() {
        return getCollectionSize("//app-goods-tile");
    }

    public WishlistItem getItem(int number) {
        return new WishlistItem(number);
    }
}