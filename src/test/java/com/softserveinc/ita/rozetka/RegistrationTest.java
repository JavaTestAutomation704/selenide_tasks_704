package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestRunner {
    @Test
    public void verifyRegistrationCapability() {

        RegistrationModal registrationModal = homePage
                .getHeader()
                .startLoggingIn()
                .startRegistration()
                .register();

        assertTrue(registrationModal.isOpen());

        SoftAssertions softAssert = new SoftAssertions();

        String actualFirstNameErrorMessage = registrationModal
                .getFirstNameErrorMessage();
        String actualLastNameErrorMessage = registrationModal
                .getLastNameErrorMessage();
        String actualPhoneNumberErrorMessage = registrationModal
                .getPhoneNumberErrorMessage();
        String actualEmailErrorMessage = registrationModal
                .getEmailErrorMessage();

        softAssert
                .assertThat(actualFirstNameErrorMessage)
                .isEqualTo("Введіть своє ім'я кирилицею");

        softAssert
                .assertThat(actualLastNameErrorMessage)
                .isEqualTo("Введіть своє прізвище кирилицею");

        softAssert
                .assertThat(actualPhoneNumberErrorMessage)
                .isEqualTo("Введіть номер мобільного телефону");

        softAssert
                .assertThat(actualEmailErrorMessage)
                .isEqualTo("Введіть свою ел. пошту");

        final String redColor = "rgb(248, 65, 71)";

        String actualFirstNameBorderColor = registrationModal.getFirstNameBorderColor(redColor);
        String actualLastNameBorderColor = registrationModal.getLastNameBorderColor(redColor);
        String actualPhoneNumberBorderColor = registrationModal.getPhoneNumberBorderColor(redColor);
        String actualEmailBorderColor = registrationModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = registrationModal.getPasswordBorderColor(redColor);

        softAssert
                .assertThat(actualFirstNameBorderColor)
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualLastNameBorderColor)
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPhoneNumberBorderColor)
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualEmailBorderColor)
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPasswordBorderColor)
                .isEqualTo(redColor);

        softAssert.assertAll();
    }
}