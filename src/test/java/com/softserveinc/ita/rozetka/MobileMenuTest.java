package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.MobileMenu;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MobileMenuTest extends TestRunner {

    @Test
    public void VerifyAreMobileMenuComponentsVisible() {
        MobileMenu mobileMenu = homePage
                .getHeader()
                .openMobileMenu();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mobileMenu.isLoginButtonVisible());
        softAssert.assertTrue(mobileMenu.isRegistrationButtonVisible());
        softAssert.assertTrue(mobileMenu.isLocationSelectionVisible());
        softAssert.assertTrue(mobileMenu.isHelpCenterButtonVisible());
        softAssert.assertTrue(mobileMenu.isContactUsButtonVisible());
        softAssert.assertAll();
    }
}
