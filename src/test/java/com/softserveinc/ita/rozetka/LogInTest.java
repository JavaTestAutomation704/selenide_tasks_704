package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
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
                .as("Error message should be displayed " +
                        "when submitting empty fields on the Log In modal")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");


        String redColorRGB = Color.RED.getRgb();

        String actualEmailBorderColor = logInModal.getEmailBorderColor(redColorRGB);
        String actualPasswordBorderColor = logInModal.getPasswordBorderColor(redColorRGB);

        softAssert
                .assertThat(actualEmailBorderColor)
                .as("Email border color should be red " +
                        "after submitting empty fields on the Log In modal")
                .contains(redColorRGB);

        softAssert
                .assertThat(actualPasswordBorderColor)
                .as("Password border color should be red " +
                        "after submitting empty fields on the Log In modal")
                .contains(redColorRGB);

        boolean isRegistrationModalOpen = logInModal
                .startRegistration()
                .isOpen();

        softAssert
                .assertThat(isRegistrationModalOpen)
                .as("Registration modal should be open")
                .isTrue();

        softAssert.assertAll();
    }
}