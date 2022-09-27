package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.WishlistPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitInvisibility;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillVisible;
import static java.lang.String.format;

public class EditWishlistNameModal {

    private final String nameFieldXpath = "//input[@formcontrolname='wishListNewName']";

    @Step("Edit wishlist name modal: fill in wishlist name '{name}'")
    public EditWishlistNameModal fillInName(String name) {
        var nameField = $x(nameFieldXpath);
        nameField.clear();
        nameField.sendKeys(name);
        return this;
    }

    @Step("Edit wishlist name modal: set wishlist as default")
    public EditWishlistNameModal setDefault() {
        $x("//label[@for='setDefault']").click();
        return this;
    }

    @Step("Edit wishlist name modal: save changes")
    public WishlistPage save() {
        var nameField = $x(nameFieldXpath);
        var wishlistName = nameField.val();

        $x("//button[contains(@class, 'navy')]").click();

        waitInvisibility("//div[contains(@class, 'modal__holder')]");
        waitTillVisible(format("//rz-cabinet-wishlist-details//h3/span[contains(text(), '%s')]", wishlistName));
        return new WishlistPage();
    }
}