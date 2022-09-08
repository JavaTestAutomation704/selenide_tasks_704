package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.Configuration;
import com.softserveinc.ita.rozetka.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.codeborne.selenide.testng.ScreenShooter;

@Listeners({ ScreenShooter.class})
public class TestRunner {
    protected HomePage homePage;

    @BeforeClass
    public void setConfiguration() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "target/allure-results";
    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage().open();
    }
}