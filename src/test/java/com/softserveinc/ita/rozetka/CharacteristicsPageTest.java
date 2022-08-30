package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CharacteristicsPageTest extends TestRunner {
    @Test
    public void verifyProductAdditionToComparisonListOnCharacteristicsPage() {
        Product lastProduct = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(HouseholdAppliances.REFRIGERATORS)
                .getProduct("last");
        String lastProductTitle = lastProduct.getTitle();

        ProductCharacteristicsPage characteristicsPage = lastProduct
                .open()
                .openCharacteristicsPage();
        String characteristicsPageUrl = characteristicsPage.getUrl();
        String characteristicsPageTitle = characteristicsPage.getTitle();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(characteristicsPageTitle)
                .as("Characteristics page title should contain last product title.")
                .contains(lastProductTitle);
        softly.assertThat(characteristicsPageTitle)
                .as("Characteristics page title should contain keyword.")
                .contains("характеристики");
        softly.assertThat(characteristicsPage.isCharacteristicsTabHighlighted())
                .as("Characteristics tab should be highlighted.")
                .isTrue();
        softly.assertThat(characteristicsPageUrl)
                .as("Characteristics page url should contain keyword.")
                .contains("characteristics");
        softly.assertThat(characteristicsPage.isCharacteristicsSectionVisible())
                .as("Characteristics page should contain characteristics section.")
                .isTrue();

        characteristicsPage.addToComparison();

        softly.assertThat(characteristicsPage.isComparisonCounterVisible())
                .as("Product should be added to comparison list.")
                .isTrue();
        softly.assertAll();
    }
}