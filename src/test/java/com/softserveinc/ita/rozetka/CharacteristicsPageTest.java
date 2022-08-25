package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CharacteristicsPageTest extends TestRunner {
    @Test
    public void verifyUserCanOpenCharacteristicsPageTest() {
        Product product = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(Subcategory.REFRIGERATORS)
                .getProduct("last");
        String productTitle = product.getTitle();

        ProductCharacteristicsPage characteristicsPage = product
                .open()
                .openCharacteristicsPage();
        String url = characteristicsPage.getUrl();
        String characteristicsPageTitle = characteristicsPage.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(characteristicsPageTitle.contains(productTitle),
                String.format("\nCharacteristics page title: '%s' \nShould contain: '%s'\n", characteristicsPageTitle, productTitle));
        softAssert.assertTrue(characteristicsPageTitle.contains("характеристики"),
                String.format("\nCharacteristics page title: '%s' \nShould contain: 'характеристики'\n", characteristicsPageTitle));
        softAssert.assertTrue(characteristicsPage.isCharacteristicsTabHighlighted(),
                "\nProduct characteristics tab is not highlighted.\n");
        softAssert.assertTrue(url.contains("characteristics"),
                String.format("\nPage url: '%s'. \nShould contain: 'characteristics'\n", url));
        softAssert.assertAll();
    }
}