package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.WishlistPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitInvisibility;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;
import static java.lang.String.format;

public class EditWishlistNameModal extends AddWishlistModal {
    private String wishlistName;

    @Step("Edit wishlist name modal: fill in wishlist name '{name}'")
    public EditWishlistNameModal fillInName(String name) {
        this.wishlistName = name;
        var nameInputField = $x("//input[@formcontrolname='wishListNewName']");
        nameInputField.clear();
        nameInputField.sendKeys(name);
        return this;
    }

    @Step("Edit wishlist name modal: set wishlist as default")
    public EditWishlistNameModal setDefault() {
        $x("//label[@for='setDefault']").click();
        return this;
    }

    @Step("Edit wishlist name modal: save changes")
    public WishlistPage save() {
        $x("//button[contains(@class, 'navy')]").click();
        waitInvisibility("//div[contains(@class, 'modal__holder')]");
        waitTillVisible(format("//rz-cabinet-wishlist-details//h3/span[contains(text(), '%s')]", wishlistName));
        return new WishlistPage();
    }
}