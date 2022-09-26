package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.WishlistPage;
import com.softserveinc.ita.rozetka.modals.EditWishlistNameModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class Wishlist {
    private final String wishlistXpath;
    private final String actionsMenuXpathTemplate;

    public Wishlist() {
        wishlistXpath = "//h3/span[contains(@class, 'caption')]/ancestor::rz-cabinet-wishlist-details";
        actionsMenuXpathTemplate = wishlistXpath + "//rz-cabinet-wishlist-menu//button[contains(@class, '%s')]";
    }

    public Wishlist(int wishlistNumber) {
        wishlistXpath = format("(//rz-cabinet-wishlist-details)[%d]", wishlistNumber);
        actionsMenuXpathTemplate = wishlistXpath + "//rz-cabinet-wishlist-menu//button[contains(@class, '%s')]";
    }

    public Wishlist(String wishlistName) {
        wishlistXpath = format("//h3//span[contains(text(), '%s')]/ancestor::rz-cabinet-wishlist-details", wishlistName);
        actionsMenuXpathTemplate = wishlistXpath + "//rz-cabinet-wishlist-menu//button[contains(@class, '%s')]";
    }

    private void openActionsMenu() {
        $x(format(actionsMenuXpathTemplate, "toggle"))
                .scrollIntoView(false)
                .click();
        waitTillVisible(wishlistXpath + "//ul[contains(@class, 'dropdown')]");
    }

    @Step("Wishlist: delete")
    public WishlistPage delete() {
        var wishlistXpath = "//rz-cabinet-wishlist-details";
        int wishlistsQuantity = getCollectionSize(wishlistXpath);

        openActionsMenu();
        $x(format(actionsMenuXpathTemplate, "delete")).click();
        $x("//button[contains(@class, 'navy')]").click(); // confirm deletion

        waitCollectionSizeDecrease(wishlistXpath, wishlistsQuantity);
        return new WishlistPage();
    }

    @Step("Wishlist: rename")
    public EditWishlistNameModal rename() {
        openActionsMenu();
        $x(format(actionsMenuXpathTemplate, "rename")).click();
        return new EditWishlistNameModal();
    }

    @Step("Wishlist: make default")
    public Wishlist makeDefault() {
        openActionsMenu();
        $x(format(actionsMenuXpathTemplate, "default")).click();
        return this;
    }

    @Step("Wishlist: select all items")
    public Wishlist selectAllItems() {
        $x(wishlistXpath + "//button[contains(@class, 'check-all')]")
                .scrollIntoView(false)
                .click();
        return this;
    }

    @Step("Wishlist: remove selected items")
    public Wishlist removeSelectedItems() {
        var wishlistCounterXpath = "//rz-wishlist//rz-icon-counter";
        var wishlistCounterQuantity = getText(wishlistCounterXpath);
        $x(wishlistXpath + "//div[contains(@class, 'wish-details__actions')]//button[contains(@class, 'delete')]")
                .scrollIntoView(false)
                .click();
        if (isVisible(wishlistCounterXpath, 2)) {
            waitForTextChange(wishlistCounterXpath, wishlistCounterQuantity);
        }
        return this;
    }

    public int getWishlistItemsQuantity() {
        return getCollectionSize(wishlistXpath + "//app-goods-tile");
    }

    public WishlistItem getItem(int number) {
        return new WishlistItem(wishlistXpath, number);
    }

    public String getWishlistName() {
        return getText(wishlistXpath + "//h3/span[1]");
    }
}