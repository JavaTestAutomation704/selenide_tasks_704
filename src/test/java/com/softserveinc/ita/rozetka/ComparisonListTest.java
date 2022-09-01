package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ComparisonItem;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.PHONES_TV_ELECTRONICS;
import static com.softserveinc.ita.rozetka.data.subcategory.PhonesTvElectronicsSubcategory.CAMERAS;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparisonListTest extends TestRunner {
    @Test
    public void verifyAddProductsToComparisonList() {
        Header header = homePage.getHeader();

        SubcategoryPage subcategoryPage = header
                .openCatalogModal()
                .openSubcategory(PHONES_TV_ELECTRONICS, CAMERAS);

        int minimumProductQuantity = 30;
        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(minimumProductQuantity);

        Product product;
        List<String> productTitles = new ArrayList<>();
        List<Long> productPrices = new ArrayList<>();
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

        ComparisonItem comparisonItem;
        SoftAssertions softly = new SoftAssertions();
        for (int i = 1; i <= comparisonProductQuantity; i++) {
            comparisonItem = comparisonPage.getComparisonItem(i);
            assertThat(comparisonItem.getItemTitle())
                    .as("Comparison item title should be in a list of added for comparison product titles")
                    .isIn(productTitles);
            softly.assertThat(comparisonItem.getItemTitle())
                    .as("Comparison %s item title should be equal to product title", i)
                    .isEqualTo(productTitles.get(comparisonProductQuantity - i));
            softly.assertThat(comparisonItem.getItemPrice())
                    .as("Comparison %s item price should be equal to product price", i)
                    .isEqualTo(productPrices.get(comparisonProductQuantity - i));
        }
        softly.assertAll();
    }
}