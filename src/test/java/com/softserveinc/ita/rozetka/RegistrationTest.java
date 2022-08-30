package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class RegistrationTest extends TestRunner {
    @Test
    public void verifyRegistrationCapability() {

        RegistrationModal registrationModal = homePage
                .getHeader()
                .startLoggingIn()
                .startRegistration()
                .register();

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(registrationModal.isOpen())
                .as("Register modal should be open")
                .isTrue();

        String actualFirstNameErrorMessage = registrationModal.getFirstNameErrorMessage();
        softAssert
                .assertThat(actualFirstNameErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть своє ім'я кирилицею");

        String actualLastNameErrorMessage = registrationModal.getLastNameErrorMessage();
        softAssert
                .assertThat(actualLastNameErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть своє прізвище кирилицею");

        String actualPhoneNumberErrorMessage = registrationModal.getPhoneNumberErrorMessage();
        softAssert
                .assertThat(actualPhoneNumberErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть номер мобільного телефону");

        String actualEmailErrorMessage = registrationModal.getEmailErrorMessage();
        softAssert
                .assertThat(actualEmailErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть свою ел. пошту");

        final String redColor = "rgb(248, 65, 71)";

        String actualFirstNameBorderColor = registrationModal.getFirstNameBorderColor(redColor);
        softAssert
                .assertThat(actualFirstNameBorderColor)
                .as("FirstName border color should be red")
                .isEqualTo(redColor);

        String actualLastNameBorderColor = registrationModal.getLastNameBorderColor(redColor);
        softAssert
                .assertThat(actualLastNameBorderColor)
                .as("LastName border color should be red")
                .isEqualTo(redColor);

        String actualPhoneNumberBorderColor = registrationModal.getPhoneNumberBorderColor(redColor);
        softAssert
                .assertThat(actualPhoneNumberBorderColor)
                .as("PhoneNumber border color should be red")
                .isEqualTo(redColor);

        String actualEmailBorderColor = registrationModal.getEmailBorderColor(redColor);
        softAssert
                .assertThat(actualEmailBorderColor)
                .as("Email border color should be red")
                .isEqualTo(redColor);

        String actualPasswordBorderColor = registrationModal.getPasswordBorderColor(redColor);
        softAssert
                .assertThat(actualPasswordBorderColor)
                .as("Password border color should be red")
                .isEqualTo(redColor);

        softAssert.assertAll();
    }
}