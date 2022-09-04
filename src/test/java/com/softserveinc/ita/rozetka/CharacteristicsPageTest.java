package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.rozetka.data.subcategory.HouseholdAppliancesSubcategory.REFRIGERATORS;

public class CharacteristicsPageTest extends TestRunner {
    @Test
    public void verifyProductAdditionToComparisonListOnCharacteristicsPage() {
        var lastProduct = homePage
                .openCategoryPage(HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(REFRIGERATORS)
                .getProduct("last");
        var lastProductTitle = lastProduct.getTitleLowerCase();

        var characteristicsPage = lastProduct
                .open()
                .openCharacteristicsPage();
        var characteristicsPageUrl = characteristicsPage.getUrl();
        var characteristicsPageTitle = characteristicsPage.getTitle();

        var softly = new SoftAssertions();
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