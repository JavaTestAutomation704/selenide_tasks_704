package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ChangeCityTest extends BaseTestRunner {
    @Test
    public void verifyCityChangeViaSideBarAndProductPage() {
        var expectedCityViaMobileMenu = "Одеса";
        var header = homePage.getHeader();
        var mainSidebar = header
                .openMainSidebar()
                .changeCity(expectedCityViaMobileMenu)
                .openMainSidebar();

        var errorMessage = "City names should be equal";

        var softly = new SoftAssertions();
        softly.assertThat(mainSidebar.getCity())
                .as(errorMessage)
                .isEqualTo(expectedCityViaMobileMenu);

        var searchTerm = "телефони";
        var searchResultsPage = header.search(searchTerm);

        assertThat(searchResultsPage.doesTitleContain(searchTerm))
                .as("Title should contain search term")
                .isTrue();

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var expectedCityViaProductPage = "Дніпро";
        searchResultsPage
                .getProduct(1)
                .open()
                .changeCity(expectedCityViaProductPage);
        mainSidebar = header.openMainSidebar();

        softly.assertThat(mainSidebar.getCity())
                .as(errorMessage)
                .isEqualTo(expectedCityViaProductPage);

        softly.assertAll();
    }
}
