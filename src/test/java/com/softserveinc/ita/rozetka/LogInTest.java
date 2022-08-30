package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class LogInTest extends TestRunner {

    @Test
    public void verifyLoggingCapability() {

        LogInModal logInModal = homePage
                .getHeader()
                .startLoggingIn();

        assertTrue(logInModal.isOpen());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(logInModal.isLogInButtonVisible());
        softAssert.assertTrue(logInModal.isRegistrationButtonVisible());

        String actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        softAssert.assertEquals(actualEmailErrorMessage,
                "Введено невірну адресу ел. пошти або номер телефону");

        final String redColor = "rgb(248, 65, 71)";

        String actualEmailBorderColor = logInModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = logInModal.getPasswordBorderColor(redColor);

        softAssert.assertEquals(actualEmailBorderColor, redColor);
        softAssert.assertEquals(actualPasswordBorderColor, redColor);

        boolean isRegistrationAvailable = logInModal
                .startRegistration()
                .isOpen();

        softAssert.assertTrue(isRegistrationAvailable);

        softAssert.assertAll();
    }
}
