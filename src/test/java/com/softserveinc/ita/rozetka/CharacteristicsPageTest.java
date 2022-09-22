package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.rozetka.data.Color.GREEN;
import static com.softserveinc.ita.rozetka.data.subcategory.HouseholdAppliancesSubcategory.REFRIGERATORS;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacteristicsPageTest extends BaseTestRunner {
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
                .as("Characteristics page title should contain last product title")
                .contains(lastProductTitle);
        softly.assertThat(characteristicsPageTitle)
                .as("Characteristics page title should contain keyword")
                .contains("характеристики");
        softly.assertThat(characteristicsPage.getCharacteristicsTabTextRgbColor())
                .as("Characteristics tab text should be green")
                .contains(GREEN.getRgb());
        softly.assertThat(characteristicsPage.getCharacteristicsTabUnderscoreRgbColor())
                .as("Characteristics tab underscore should be green")
                .contains(GREEN.getRgb());
        softly.assertThat(characteristicsPageUrl)
                .as("Characteristics page url should contain keyword")
                .contains("characteristics");
        softly.assertThat(characteristicsPage.isCharacteristicsSectionVisible())
                .as("Characteristics page should contain characteristics section")
                .isTrue();

        characteristicsPage.addToComparison();

        softly.assertThat(characteristicsPage.isComparisonCounterVisible())
                .as("Product should be added to comparison list")
                .isTrue();
        softly.assertAll();
    }

    @Test
    public void verifyProductOnSaleOnPromoPage() {
        var promoPage = homePage
                .openCategoryPage(Category.PROMOTION)
                .openPromoPage();

        var isUaLanguage = homePage
                .getHeader()
                .isLanguageSelected(Language.UA);

        assertThat(isUaLanguage)
                .as("Language should be ukrainian")
                .isTrue();

        assertThat(promoPage.getTitle())
                .as("Title should be correct")
                .isEqualTo("ТОП-товари");

        assertThat(promoPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(40);

        var softly = new SoftAssertions();
        for (int number = 1; number < 40; number += 3) {
            softly.assertThat(promoPage
                            .getProduct(number)
                            .isOnSale())
                    .as(number + " product should be on sale")
                    .isTrue();
        }
        softly.assertAll();
    }
}