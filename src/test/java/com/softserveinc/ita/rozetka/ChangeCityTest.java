package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ChangeCityTest extends TestRunner {
    @Test
    public void verifyCityChangeViaSideBarAndProductPage() {
        var expectedCityViaMobileMenu = "Одеса";
        var header = homePage.getHeader();
        var mainSidebar = header
                .openMainSidebar()
                .changeCity(expectedCityViaMobileMenu)
                .openMainSidebar();

        var errorMessage = "City names should be equal";

        var softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainSidebar.getCity())
                .as(errorMessage)
                .isEqualTo(expectedCityViaMobileMenu);

        var searchResultsPage = header.search("Планшети");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var expectedCityViaProductPage = "Дніпро";
        searchResultsPage
                .getProduct(1)
                .open()
                .changeCity(expectedCityViaProductPage);
        mainSidebar = header.openMainSidebar();

        softAssertions.assertThat(mainSidebar.getCity())
                .as(errorMessage)
                .isEqualTo(expectedCityViaProductPage);

        softAssertions.assertAll();
    }
}
