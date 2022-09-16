package com.softserveinc.ita.rozetka.utils;

import org.testng.annotations.BeforeMethod;

public class LoginTestRunner extends TestRunner {

    @BeforeMethod
    public void signInByFacebook() {
        homePage = homePage
                .getHeader()
                .openSignInModal()
                .signInViaFacebookWithDefaultCreds();
    }
}
