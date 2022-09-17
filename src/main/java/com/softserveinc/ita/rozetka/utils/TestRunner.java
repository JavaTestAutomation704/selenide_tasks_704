package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(AllureScreenShooter.class)
public class TestRunner {
    protected HomePage homePage;

    @BeforeClass
    public void setConfiguration() {
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "target/allure-results";
    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage().open();
    }

    @AfterMethod
    public void clear() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}