package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.HomePage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(AllureScreenShooter.class)
public class BaseTestRunner {
    protected HomePage homePage;
    protected static ConfigProperties configProperties;

    @BeforeSuite
    public void setConfigProperties() throws IOException {
        if (configProperties == null) {
            configProperties = new ConfigProperties();
        }
    }

    @BeforeClass
    public void setConfiguration() {
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "target/allure-results";
        Configuration.browser = configProperties.getBrowser();
        if (configProperties.isWithSelenoid()) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            var desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("enableVNC", true);
            Configuration.browserCapabilities = desiredCapabilities;
        }
    }

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage().open();
    }

    @AfterMethod
    public void close() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}