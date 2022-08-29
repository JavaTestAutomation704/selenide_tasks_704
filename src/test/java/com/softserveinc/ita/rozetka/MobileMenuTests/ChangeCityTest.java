package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void verifyCityChangeViaSideBarAndProductPage() {
        String expectedCityViaMobileMenu = "Одеса";
        Header header = homePage.getHeader();
        MobileMenu mobileMenu = header
                .openMobileMenu()
                .changeCity(expectedCityViaMobileMenu)
                .openMobileMenu();

        String actualCityViaMobileMenu = mobileMenu.getCity();

        String errorMessage = "City names should be equal";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualCityViaMobileMenu)
                .as(errorMessage)
                .isEqualTo(expectedCityViaMobileMenu);

        String expectedCityViaProductPage = "Дніпро";
        header
                .search("Планшети")
                .openProductPage(1)
                .changeCity(expectedCityViaProductPage);
        mobileMenu = header.openMobileMenu();

        String actualCityViaProductPage = mobileMenu.getCity();

        softAssertions.assertThat(actualCityViaProductPage)
                .as(errorMessage)
                .isEqualTo(expectedCityViaProductPage);
        softAssertions.assertAll();
    }
}