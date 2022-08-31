package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.components.SmallCart;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class NavigateToHomePageTest extends TestRunner {

    @Test
    public void verifyOpeningHomePageViaLogo() {
        Header header = homePage.getHeader();
        SmallCart smallCart = homePage.getSmallCart();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(header.isShoppingCartCounterVisible())
                .as("Cart should be empty")
                .isFalse();
        softly.assertThat(smallCart.isOpen())
                .as("Small cart should not be open")
                .isFalse();

        SubcategoryPage subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);

        softly.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        Product product = subcategoryPage.getProduct(1);
        product.addToShoppingCart();

        softly.assertThat(product.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should be open")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        smallCart
                .close()
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS);
        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should be open")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        smallCart.close();
        header
                .openMobileMenu()
                .openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should not be open")
                .isFalse();
        softly.assertAll();
    }
}
