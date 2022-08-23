package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void changeCityTest() {
        String expectedCity = "Одеса";
        Header header = homePage
                .getHeader()
                .openMobileMenu()
                .changeCity(expectedCity);
        String actualCity = header.openMobileMenu().getCity();
        Assert.assertEquals(actualCity, expectedCity);
        // will be added one more assert after merge
    }
}