package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import com.softserveinc.ita.rozetka.components.Header;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeCityTest extends TestRunner {
    @Test()
    public void changeCityTest() {
        String expectedCity = "Одеса";
        Header header = homePage
                .getHeader()
                .openSidebar()
                .changeCity(expectedCity);
        String actualCity = header.openSidebar().getCity();
        Assert.assertEquals(actualCity, expectedCity);
        // will be added one more assert after merge
    }
}
