package com.softserveinc.ita.rozetka.utils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class LogInViaFacebookTestRunner extends TestRunner {

    @BeforeMethod
    public void logInByFacebook() throws IOException {
        var property = new ConfigProperties();
        homePage = homePage
                .getHeader()
                .startLogging()
                .logInViaFacebook(property.getFacebookUserEmailOrPhone(), property.getFacebookUserPassword());
    }
}
