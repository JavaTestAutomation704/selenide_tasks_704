package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenHomePageViaLogoTest extends TestRunner {

    @Test
    public void verifyHomePageIsOpen() {
        homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS)
                .addToCart(1)
                .getHeader()
                .openHomePageViaLogo()
                .closeSmallCartSection()
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .getHeader()
                .openHomePageViaLogo();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isSmallCartSectionVisible(), "Small cart is not visible.");
        softAssert.assertTrue(homePage.isMainCategoriesSectionVisible(), "Main categories is not visible.");

        homePage
                .closeSmallCartSection()
                .getHeader()
                .openMobileMenu()
                .openHomePageViaLogo();

        softAssert.assertFalse(homePage.isSmallCartSectionVisible(), "Small cart is visible.");
        softAssert.assertTrue(homePage.isMainCategoriesSectionVisible(), "Main categories is not visible.");
        softAssert.assertAll();
    }
}
