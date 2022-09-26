package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.WishlistPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class AddWishlistModal {

    @Step("Add wishlist modal: fill in wishlist name '{name}'")
    public AddWishlistModal fillInName(String name) {
        $x("//input[@formcontrolname='wishListName']").sendKeys(name);
        return this;
    }

    @Step("Add wishlist modal: set wishlist as default")
    public AddWishlistModal setDefault() {
        $x("//label[@for='setDefault']").click();
        return this;
    }

    @Step("Add wishlist modal: add wishlist")
    public WishlistPage add() {
        var wishlistXpath = "//rz-cabinet-wishlist-details";
        int wishlistsQuantity = getCollectionSize(wishlistXpath);

        $x("//button[@type='submit']").click();

        waitCollectionSizeIncrease(wishlistXpath, wishlistsQuantity);
        return new WishlistPage();
    }
}