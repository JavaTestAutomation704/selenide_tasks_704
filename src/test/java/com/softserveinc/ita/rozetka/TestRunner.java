package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestRunner {
    protected HomePage homePage;

    @BeforeClass
    public void setConfiguration() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage().open();
    }
}