package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.LAPTOPS_AND_COMPUTERS;
import static com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory.TABLETS;
import static java.lang.String.format;

public class PaginationTest extends BaseTestRunner {

    @Test
    public void verifyPageNumbersPaginationAndLoadMoreProductsComponentsWorks() {
        var subcategoryPage = homePage
                .openCategoryPage(LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(TABLETS);
        var pagePagination = subcategoryPage.getPagePagination();

        var softly = new SoftAssertions();
        softly.assertThat(pagePagination.isNextPageButtonEnabled())
                .as("Button should be enabled")
                .isTrue();
        softly.assertThat(pagePagination.isPreviousPageButtonEnabled())
                .as("Button should be disabled")
                .isFalse();

        int pageNumber = 5;
        pagePagination.switchToPage(pageNumber);

        int productsQuantity = subcategoryPage.getProductsQuantity();

        var pageNumberTemplate = "page=%d";
        softly.assertThat(subcategoryPage.getUrl())
                .as("Products quantity should be correct")
                .contains(format(pageNumberTemplate, pageNumber));

        pagePagination.loadMoreProducts();

        softly.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be correct")
                .isEqualTo(productsQuantity * 2);
        softly.assertThat(subcategoryPage.getUrl())
                .as("Products quantity should be correct")
                .contains(format(pageNumberTemplate, pageNumber + 1));
        softly.assertThat(pagePagination.isPreviousPageButtonEnabled())
                .as("Button should be enabled")
                .isTrue();
        softly.assertThat(pagePagination.isNextPageButtonEnabled())
                .as("Button should be enabled")
                .isTrue();

        var lastPageNumber = pagePagination.getLastPageNumber();
        pagePagination.switchToPage(lastPageNumber);

        softly.assertThat(subcategoryPage.getUrl())
                .as("Products quantity should be correct")
                .contains(format(pageNumberTemplate, lastPageNumber));
        softly.assertThat(pagePagination.isPreviousPageButtonEnabled())
                .as("Button should be enabled")
                .isTrue();
        softly.assertThat(pagePagination.isNextPageButtonEnabled())
                .as("Button should disabled")
                .isFalse();
        softly.assertAll();
    }
}
