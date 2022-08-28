package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CharacteristicsPageTest extends TestRunner {
    @Test
    public void verifyUserCanAddProductToComparisonOnCharacteristicsPage() {
        Product product = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(HouseholdAppliances.REFRIGERATORS)
                .getProduct("last");
        String productTitle = product.getTitle();

        ProductCharacteristicsPage characteristicsPage = product
                .open()
                .openCharacteristicsPage();
        String url = characteristicsPage.getUrl();
        String characteristicsPageTitle = characteristicsPage.getTitle();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(characteristicsPageTitle)
                .as("Characteristics page title")
                .contains(productTitle);
        softly.assertThat(characteristicsPageTitle)
                .as("Characteristics page title")
                .contains("характеристики");
        softly.assertThat(characteristicsPage.isCharacteristicsTabHighlighted())
                .as("Product characteristics tab is highlighted")
                .isTrue();
        softly.assertThat(url)
                .as("Page url")
                .contains("characteristics");
        softly.assertThat(characteristicsPage.isCharacteristicsSectionVisible())
                .as("Product characteristics section is present")
                .isTrue();

        characteristicsPage.addToComparison();

        softly.assertThat(characteristicsPage.isComparisonCounterVisible())
                .as("Comparison counter is visible")
                .isTrue();
        softly.assertAll();
    }
}