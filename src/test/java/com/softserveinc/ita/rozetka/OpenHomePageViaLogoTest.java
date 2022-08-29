package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.SmallCart;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class OpenHomePageViaLogoTest extends TestRunner {

    @Test
    public void verifyOpeningHomePageViaLogo() {
        Header header = homePage.getHeader();
        SmallCart smallCart = homePage.getSmallCart();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(header.isShoppingCartCounterVisible())
                .as("Cart should be empty")
                .isFalse();
        softly.assertThat(smallCart.isOpen())
                .as("Small cart should not be visible")
                .isFalse();

        homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS)
                .getProduct(1)
                .addToShoppingCart();

        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should be visible")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        smallCart
                .close()
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS);
        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should be visible")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        smallCart.close();
        header
                .openMobileMenu()
                .openHomePageViaLogo();

        softly.assertThat(smallCart.isOpen())
                .as("Small cart should not be visible")
                .isFalse();
        softly.assertAll();
    }
}
