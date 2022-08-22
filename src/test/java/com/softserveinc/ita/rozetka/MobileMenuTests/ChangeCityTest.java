package com.softserveinc.ita.rozetka.MobileMenuTests;

import com.softserveinc.ita.rozetka.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangeCityTest extends TestRunner {
    @DataProvider(name = "city")
    public Object[][] data() {
        return new Object[][]{
                {"Одеса"},
                {"Запоріжжя"}
        };
    }

    @Test(dataProvider = "city")
    public void changeCityTest(String city) {
        Header header = homePage.getHeader().openMobileMenu().changeCity(city);
        Assert.assertEquals(header.openMobileMenu().getCity(), city);
    }
}
