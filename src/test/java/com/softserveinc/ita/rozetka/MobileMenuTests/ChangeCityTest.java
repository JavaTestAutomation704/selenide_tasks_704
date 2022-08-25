package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void verifyChangeCityTest() {
        String expectedCityViaMobileMenu = "Одеса";
        Header header = homePage
                .getHeader()
                .openMobileMenu()
                .changeCity(expectedCityViaMobileMenu);

        String actualCityViaMobileMenu = header
                .openMobileMenu()
                .getCity();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCityViaMobileMenu, expectedCityViaMobileMenu);

        String expectedCityViaProductPage = "Дніпро";
        String actualCityViaProductPage = header
                .search("Планшети")
                .openProductPage(1)
                .changeCity(expectedCityViaProductPage)
                .getHeader()
                .openMobileMenu()
                .getCity();

        softAssert.assertEquals(actualCityViaProductPage, expectedCityViaProductPage);
        softAssert.assertAll();
    }
}
