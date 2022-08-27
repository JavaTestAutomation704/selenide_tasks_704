package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.LogInModal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogInTest extends TestRunner {

    @Test
    public void verifyLoggingCapability() {

        LogInModal logInModal = homePage
                .getHeader()
                .startLoggingIn();

        assertThat(logInModal.isOpen())
                .withFailMessage("Log In modal did not open")
                .isTrue();

        SoftAssertions softAssert = new SoftAssertions();

        softAssert
                .assertThat(logInModal.isLogInButtonVisible())
                .withFailMessage("Log In button did not display")
                .isTrue();

        softAssert
                .assertThat(logInModal.isRegistrationButtonVisible())
                .withFailMessage("Registration button did not display")
                .isTrue();

        String actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        softAssert
                .assertThat(actualEmailErrorMessage)
                .withFailMessage("Expected error message did not appear")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");

        final String redColor = "rgb(248, 65, 71)";

        String actualEmailBorderColor = logInModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = logInModal.getPasswordBorderColor(redColor);

        softAssert
                .assertThat(actualEmailBorderColor)
                .withFailMessage("Incorrect border color")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPasswordBorderColor)
                .withFailMessage("Incorrect border color")
                .isEqualTo(redColor);

        boolean isRegistrationAvailable = logInModal
                .startRegistration()
                .isOpen();

        softAssert
                .assertThat(isRegistrationAvailable)
                .withFailMessage("Registration is unavailable via Log In modal")
                .isTrue();

        softAssert.assertAll();
    }
}