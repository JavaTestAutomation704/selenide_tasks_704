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
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var logInModal = header.startLogging();

        assertThat(logInModal.isOpened())
                .as("Log In modal should be opened")
                .isTrue();

        assertThat(logInModal.isLogInButtonVisible())
                .as("Log In button should be displayed on the Log In modal")
                .isTrue();

        assertThat(logInModal.isRegistrationButtonVisible())
                .as("Registration button should be displayed on the Log In modal")
                .isTrue();

        assertThat(logInModal.isRemindPasswordButtonVisible())
                .as("Remind password button should be displayed on the Log In modal")
                .isTrue();

        var softAssertions = new SoftAssertions();

        var actualEmailErrorMessage = logInModal
                .logIn()
                .getEmailErrorMessage();

        softAssertions
                .assertThat(actualEmailErrorMessage)
                .as("Error message should be displayed when submitting empty fields on the Log In modal")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");

        var redColor = Color.RED.getRgb();

        var isActualEmailBorderColorCorrect = logInModal.isEmailBorderColorCorrect(redColor);
        var isActualPasswordBorderColorCorrect = logInModal.isPasswordBorderColorCorrect(redColor);

        softAssertions
                .assertThat(isActualEmailBorderColorCorrect)
                .as("Email border color should be red after submitting empty fields on the Log In modal")
                .isTrue();

        softAssertions
                .assertThat(isActualPasswordBorderColorCorrect)
                .as("Password border color should be red after submitting empty fields on the Log In modal")
                .isTrue();

        logInModal.remindPassword();
        assertThat(logInModal.isGetTemporaryPasswordButtonVisible())
                .as("Get temporary password button should be displayed on the Log In modal")
                .isTrue();

        assertThat(logInModal.isRememberPasswordButtonVisible())
                .as("Remember password button should be displayed on the Log In modal")
                .isTrue();

        actualEmailErrorMessage = logInModal
                .getTemporaryPassword()
                .getEmailErrorMessage();

        softAssertions
                .assertThat(actualEmailErrorMessage)
                .as("Error message should be displayed when submitting empty fields on the Log In modal")
                .isEqualTo("Введено невірну адресу ел. пошти або номер телефону");

        isActualEmailBorderColorCorrect = logInModal.isEmailBorderColorCorrect(redColor);

        softAssertions
                .assertThat(isActualEmailBorderColorCorrect)
                .as("Email border color should be red after submitting empty email field on the Log In modal")
                .isTrue();

        logInModal.rememberPassword();

        var isRegistrationModalOpened = logInModal
                .startRegistration()
                .isOpened();

        softAssertions
                .assertThat(isRegistrationModalOpened)
                .as("Registration modal should be opened")
                .isTrue();

        softAssertions.assertAll();
    }
}