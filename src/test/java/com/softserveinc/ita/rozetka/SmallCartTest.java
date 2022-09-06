package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmallCartTest extends TestRunner {
    Header header;

    @BeforeMethod
    public void clearShoppingCart() {
        header = homePage.getHeader();

        if (header.isShoppingCartCounterVisible()) {
            ShoppingCartModal shoppingCartModal = header
                    .openShoppingCartModal()
                    .clear();
            if (shoppingCartModal.isCloseButtonVisible()) {
                shoppingCartModal.close();
            } else {
                homePage.back();
            }
        }
    }

    @Test
    public void verifyUserCanAddProductsToSmallCart(){
        var smallCart = homePage.getSmallCart();

        var softly = new SoftAssertions();
        softly.assertThat(homePage.isSmallCartSectionVisible())
                .as("Small cart should not be visible")
                .isFalse();

        var subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);

        softly.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        var firstProduct = subcategoryPage.getProduct(1);
        firstProduct.addToShoppingCart();

        softly.assertThat(firstProduct.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        long firstProductPrice = firstProduct.getPrice();

        var secondProduct = subcategoryPage.getProduct(2);
        secondProduct.addToShoppingCart();

        softly.assertThat(secondProduct.isInShoppingCart())
                .as("Second product should be added to shopping cart")
                .isTrue();

        long secondProductPrice = secondProduct.getPrice();
        homePage.getHeader().openHomePageViaLogo();

        softly.assertThat(homePage.isSmallCartSectionVisible())
                .as("Small cart should be visible")
                .isTrue();

        softly.assertThat(smallCart.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isEqualTo(2);

        softly.assertThat(smallCart.getTotalPrice())
                .as("Products total price should be sufficient")
                .isEqualTo(firstProductPrice + secondProductPrice);
        softly.assertAll();
    }
}
