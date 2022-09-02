package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class LogInTest extends TestRunner {

    @Test
    public void verifyLoggingCapability() {

        var logInModal = homePage
                .getHeader()
                .startLogging();

        assertThat(logInModal.isOpen())
                .as("Log In modal should be open")
                .isTrue();

        var softAssert = new SoftAssertions();

        softAssert
                .assertThat(logInModal.isLogInButtonVisible())
                .as("Log In button should be displayed on the Log In modal")
                .isTrue();

        softAssert
                .assertThat(logInModal.isRegistrationButtonVisible())
                .as("Registration button should be displayed on the Log In modal")
                .isTrue();

        var isUaLanguageSelected = homePage
                .getHeader()
                .isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        softAssert
                .assertThat(actualEmailErrorMessage)
                .as("Error message should be displayed when submitting empty fields on the Log In modal")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");


        var redColor = Color.RED.getRgb();

        var isActualEmailBorderColorCorrect = logInModal.isEmailBorderColorCorrect(redColor);
        var isActualPasswordBorderColorCorrect = logInModal.isPasswordBorderColorCorrect(redColor);

        softAssert
                .assertThat(isActualEmailBorderColorCorrect)
                .as("Email border color should be red after submitting empty fields on the Log In modal")
                .isTrue();


        softAssert
                .assertThat(isActualPasswordBorderColorCorrect)
                .as("Password border color should be red after submitting empty fields on the Log In modal")
                .isTrue();

        var isRegistrationModalOpen = logInModal
                .startRegistration()
                .isOpen();

        softAssert
                .assertThat(isRegistrationModalOpen)
                .as("Registration modal should be open")
                .isTrue();

        softAssert.assertAll();
    }
}