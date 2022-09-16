package com.softserveinc.ita.rozetka.utils;

import org.testng.annotations.BeforeMethod;

public class LogInViaFacebookTestRunner extends TestRunner {

    @BeforeMethod
    public void logInByFacebook() {
        var property = new ConfigProperties();
        homePage = homePage
                .getHeader()
                .startLogging()
                .logInViaFacebook(property.getFacebookUserEmailOrPhone(), property.getFacebookUserPassword());
    }
}
