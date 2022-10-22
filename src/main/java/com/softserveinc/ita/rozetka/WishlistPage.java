package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Wishlist;
import com.softserveinc.ita.rozetka.modals.AddWishlistModal;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

@Getter
public class WishlistPage extends BasePage {

    private final Wishlist defaultWishlist = new Wishlist();

    public Wishlist getWishlist(int wishlistNumber) {
        return new Wishlist(wishlistNumber);
    }

    public Wishlist getWishlist(String wishlistName) {
        return new Wishlist(wishlistName);
    }

    public int getWishlistsQuantity() {
        return getCollectionSize("//rz-cabinet-wishlist-details");
    }

    public List<String> getWishlistNames() {
        return getElementsText("//rz-cabinet-wishlist-details//h3/span[1]");
    }

    @Step("Wishlist page: add wishlist")
    public AddWishlistModal addWishlist() {
        $x("//button[contains(@class, 'add-list')]").click();
        return new AddWishlistModal();
    }
}