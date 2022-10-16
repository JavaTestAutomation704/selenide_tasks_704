package com.softserveinc.ita.rozetka.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.utils.AllureScreenShooter;
import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(AllureScreenShooter.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com/softserveinc/ita/rozetka/cucumber/step/definitions")
public class BaseTestRunner extends AbstractTestNGCucumberTests {

    protected HomePage homePage;

    @BeforeClass
    public void setConfiguration() throws IOException {
        Configuration.timeout = 30000;
        Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "target/allure-results";
        var configProperties = new ConfigProperties();
        Configuration.browser = configProperties.getBrowser();
        if (configProperties.isWithSelenoid()) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            var desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("enableVNC", true);
            Configuration.browserCapabilities = desiredCapabilities;
        }
    }

    @AfterMethod
    public void close() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}