package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.LogInModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogInTest extends TestRunner {

    @Test
    public void verifyLoggingCapability() {
        final String redColor = "rgb(248, 65, 71)";
        final String errorMessageIncorrectEmail = "Введено невірну адресу ел. пошти або номер телефону";

        LogInModal logInModal = homePage
                .getHeader()
                .startLoggingIn();

        boolean isLogInModalOpen = logInModal.isOpen();
        boolean isLogInButtonVisible = logInModal.isLogInButtonVisible();
        boolean isRegistrationButtonVisible = logInModal.isRegistrationButtonVisible();

        String actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        String actualEmailBorderColor = logInModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = logInModal.getPasswordBorderColor(redColor);

        boolean isRegistrationAvailable = logInModal
                .startRegistration()
                .isOpen();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isLogInModalOpen);
        softAssert.assertTrue(isLogInButtonVisible);
        softAssert.assertTrue(isRegistrationButtonVisible);
        softAssert.assertEquals(actualEmailErrorMessage, errorMessageIncorrectEmail);
        softAssert.assertEquals(actualEmailBorderColor, redColor);
        softAssert.assertEquals(actualPasswordBorderColor, redColor);
        softAssert.assertTrue(isRegistrationAvailable);

        softAssert.assertAll();
    }
}
