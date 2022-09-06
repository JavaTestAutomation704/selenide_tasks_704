package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.softserveinc.ita.rozetka.data.Category.*;
import static com.softserveinc.ita.rozetka.data.subcategory.AlcoholicBeveragesAndProductsSubcategory.*;
import static com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory.ASUS;
import static com.softserveinc.ita.rozetka.data.subcategory.PhonesTvElectronicsSubcategory.CAMERAS;
import static com.softserveinc.ita.rozetka.data.subcategory.SportAndHobbiesSubcategory.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparisonListTest extends TestRunner {
    Header header;

    @BeforeMethod
    @AfterMethod
    private void clearComparisonList() {
        header = homePage.getHeader();
        int counter = 0;
        while (header.isComparisonListCounterVisible() && counter < 10) {
            header
                    .openComparisonListModal()
                    .clear()
                    .close();
            counter++;
        }
    }

    @Test
    public void verifyRemoveCategoriesFromComparisonList() {
        var categoriesWithSubcategories = new HashMap<Category, List<ISubcategory>>();
        categoriesWithSubcategories.put(ALCOHOLIC_BEVERAGES_AND_PRODUCTS, asList(COGNAC, WHISKY));
        categoriesWithSubcategories.put(SPORT_AND_HOBBIES, asList(HOVERBOARDS, FISHING_RODS));
        categoriesWithSubcategories.put(LAPTOPS_AND_COMPUTERS, asList(ASUS));

        var subcategoriesCounter = new AtomicInteger();
        categoriesWithSubcategories.forEach((category, subcategories) -> {
            for (var subcategory : subcategories) {
                var subcategoryPage = header
                        .openCatalogModal()
                        .openSubcategory(category, subcategory);
                if (category == ALCOHOLIC_BEVERAGES_AND_PRODUCTS) {
                    subcategoryPage
                            .getDrinkingAgeConfirmationModal()
                            .confirm();
                }
                subcategoryPage
                        .getProduct(1)
                        .addToComparisonList();
                subcategoriesCounter.getAndIncrement();

                assertThat(header.getComparisonListProductQuantity())
                        .as("Incorrect product comparison list counter quantity")
                        .isEqualTo(subcategoriesCounter.intValue());
            }
        });

        var comparisonListModal = header.openComparisonListModal();
        for (int i = subcategoriesCounter.intValue(); i > 0; i--) {
            comparisonListModal.remove(i);
            if (header.isComparisonListCounterVisible()) {
                assertThat(header.getComparisonListProductQuantity())
                        .as("Incorrect product comparison list counter quantity")
                        .isEqualTo(i - 1);
            }
        }
        comparisonListModal.close();

        assertThat(header.isComparisonListCounterVisible())
                .as("Comparison list counter should not be visible")
                .isFalse();
    }

    @Test
    public void verifyAddProductsToComparisonList() {
        var subcategoryPage = header
                .openCatalogModal()
                .openSubcategory(PHONES_TV_ELECTRONICS, CAMERAS);

        int minimumProductQuantity = 30;
        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(minimumProductQuantity);

        Product product;
        var productTitles = new ArrayList<>();
        var productPrices = new ArrayList<>();
        int productNumber = 3;
        while (productNumber < minimumProductQuantity) {
            product = subcategoryPage.getProduct(productNumber);
            productTitles.add(product.getTitle());
            productPrices.add(product.getPrice());
            product.addToComparisonList();
            productNumber += 6;
        }
        product = subcategoryPage.getProduct("last");
        productTitles.add(product.getTitle());
        productPrices.add(product.getPrice());
        product.addToComparisonList();

        int comparisonProductQuantity = 6;
        assertThat(header.getComparisonListProductQuantity())
                .as("Incorrect number of products in comparison list")
                .isEqualTo(comparisonProductQuantity);

        ComparisonPage comparisonPage = header
                .openComparisonListModal()
                .openComparisonPage(CAMERAS);

        assertThat(comparisonPage.getComparisonItemQuantity())
                .as("Comparison item quantity should be equal to quantity of products added for comparison")
                .isEqualTo(comparisonProductQuantity);

        SoftAssertions softly = new SoftAssertions();
        for (int i = 1; i <= comparisonProductQuantity; i++) {
            var comparisonItem = comparisonPage.getComparisonItem(i);
            assertThat(comparisonItem.getItemTitle())
                    .as("Comparison item title should be in a list of added for comparison product titles")
                    .isIn(productTitles);

            int reverseOrderProductNumber = comparisonProductQuantity - i;
            softly.assertThat(comparisonItem.getItemTitle())
                    .as(i + " comparison item title should be equal to product title")
                    .isEqualTo(productTitles.get(reverseOrderProductNumber));
            softly.assertThat(comparisonItem.getItemPrice())
                    .as(i + " comparison item price should be equal to product price")
                    .isEqualTo(productPrices.get(reverseOrderProductNumber));
        }
        softly.assertAll();
    }
}