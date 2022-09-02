package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MainSidebar;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ChangeCityTest extends TestRunner {
    @Test
    public void verifyCityChangeViaSideBarAndProductPage() {
        String expectedCityViaMobileMenu = "Одеса";
        Header header = homePage.getHeader();
        MainSidebar mainSidebar = header
                .openMainSidebar()
                .changeCity(expectedCityViaMobileMenu)
                .openMainSidebar();

        String errorMessage = "City names should be equal";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainSidebar.getCity())
                .as(errorMessage)
                .isEqualTo(expectedCityViaMobileMenu);

        SearchResultsPage searchResultsPage = header.search("Планшети");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        String expectedCityViaProductPage = "Дніпро";
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
