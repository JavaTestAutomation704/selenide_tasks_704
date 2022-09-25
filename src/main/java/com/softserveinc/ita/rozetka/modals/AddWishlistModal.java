package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.WishlistPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitInvisibility;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;
import static java.lang.String.format;

public class AddWishlistModal {
    private String wishlistName;

    @Step("Add wishlist modal: fill in wishlist name '{name}'")
    public AddWishlistModal fillInName(String name) {
        this.wishlistName = name;
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
        $x("//button[@type='submit']").click();
        waitInvisibility("//div[contains(@class, 'modal__holder')]");
        waitTillVisible(format("//rz-cabinet-wishlist-details//h3/span[contains(text(), '%s')]", wishlistName));
        return new WishlistPage();
    }
}