package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void verifyChangeCity() {
        String expectedCityViaMobileMenu = "Одеса";
        Header header = homePage.getHeader();
        MobileMenu mobileMenu = header
                .openMobileMenu()
                .changeCity(expectedCityViaMobileMenu)
                .openMobileMenu();

        String actualCityViaMobileMenu = mobileMenu
                .getCity();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCityViaMobileMenu, expectedCityViaMobileMenu);

        String expectedCityViaProductPage = "Дніпро";
        header = header
                .search("Планшети")
                .openProductPage(1)
                .changeCity(expectedCityViaProductPage)
                .getHeader();
        mobileMenu = header.openMobileMenu();

        String actualCityViaProductPage = mobileMenu
                .getCity();

        softAssert.assertEquals(actualCityViaProductPage, expectedCityViaProductPage);
        softAssert.assertAll();
    }
}
