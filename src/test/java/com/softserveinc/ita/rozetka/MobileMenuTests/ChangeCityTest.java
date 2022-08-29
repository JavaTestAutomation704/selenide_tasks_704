package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void verifyChangeCityViaSideBarAndProductPage() {
        String expectedCityViaMobileMenu = "Одеса";
        Header header = homePage.getHeader();
        MobileMenu mobileMenu = header
                .openMobileMenu()
                .changeCity(expectedCityViaMobileMenu)
                .openMobileMenu();

        String actualCityViaMobileMenu = mobileMenu.getCity();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualCityViaMobileMenu)
                .as("Cities should be equal")
                .isEqualTo(expectedCityViaMobileMenu);

        String expectedCityViaProductPage = "Дніпро";
        header
                .search("Планшети")
                .openProductPage(1)
                .changeCity(expectedCityViaProductPage);
        mobileMenu = header.openMobileMenu();

        String actualCityViaProductPage = mobileMenu.getCity();

        softAssertions.assertThat(actualCityViaProductPage)
                .as("Cities should be equal")
                .isEqualTo(expectedCityViaProductPage);
        softAssertions.assertAll();
    }
}