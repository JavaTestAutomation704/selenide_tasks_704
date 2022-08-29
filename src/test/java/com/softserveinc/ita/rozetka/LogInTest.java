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
                .as("Log In modal should be open")
                .isTrue();

        SoftAssertions softAssert = new SoftAssertions();

        softAssert
                .assertThat(logInModal.isLogInButtonVisible())
                .as("Log In button should be displayed on the Log In modal")
                .isTrue();

        softAssert
                .assertThat(logInModal.isRegistrationButtonVisible())
                .as("Registration button should be displayed on the Log In modal")
                .isTrue();

        String actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        softAssert
                .assertThat(actualEmailErrorMessage)
                .as("Error message 'Введено невірну адресу ел. пошти або номер телефону' " +
                        "should be displayed after submitting with empty fields on the Log In modal")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");

        final String redColor = "rgb(248, 65, 71)";

        String actualEmailBorderColor = logInModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = logInModal.getPasswordBorderColor(redColor);

        softAssert
                .assertThat(actualEmailBorderColor)
                .as("Email border color should be red " +
                        "after submitting with empty fields on the Log In modal")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPasswordBorderColor)
                .as("Password border color should be red " +
                        "after submitting with empty fields on the Log In modal")
                .isEqualTo(redColor);

        boolean isRegistrationAvailable = logInModal
                .startRegistration()
                .isOpen();

        softAssert
                .assertThat(isRegistrationAvailable)
                .as("Registration should be available via Log In modal")
                .isTrue();

        softAssert.assertAll();
    }
}