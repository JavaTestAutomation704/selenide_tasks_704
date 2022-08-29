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
        String actualLastNameErrorMessage = registrationModal.getLastNameErrorMessage();
        String actualPhoneNumberErrorMessage = registrationModal.getPhoneNumberErrorMessage();
        String actualEmailErrorMessage = registrationModal.getEmailErrorMessage();

        softAssert
                .assertThat(actualFirstNameErrorMessage)
                .as("Error message (Введіть своє ім'я кирилицею) doesn't appear")
                .isEqualTo("Введіть своє ім'я кирилицею");

        softAssert
                .assertThat(actualLastNameErrorMessage)
                .as("Error message (Введіть своє прізвище кирилицею) doesn't appear")
                .isEqualTo("Введіть своє прізвище кирилицею");

        softAssert
                .assertThat(actualPhoneNumberErrorMessage)
                .as("Error message (Введіть номер мобільного телефону) doesn't appear")
                .isEqualTo("Введіть номер мобільного телефону");

        softAssert
                .assertThat(actualEmailErrorMessage)
                .as("Error message (Введіть свою ел. пошту) doesn't appear")
                .isEqualTo("Введіть свою ел. пошту");

        final String redColor = "rgb(248, 65, 71)";

        String actualFirstNameBorderColor = registrationModal.getFirstNameBorderColor(redColor);
        String actualLastNameBorderColor = registrationModal.getLastNameBorderColor(redColor);
        String actualPhoneNumberBorderColor = registrationModal.getPhoneNumberBorderColor(redColor);
        String actualEmailBorderColor = registrationModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = registrationModal.getPasswordBorderColor(redColor);

        softAssert
                .assertThat(actualFirstNameBorderColor)
                .as("FirstName border color should be red")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualLastNameBorderColor)
                .as("LastName border color should be red")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPhoneNumberBorderColor)
                .as("PhoneNumber border color should be red")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualEmailBorderColor)
                .as("Email border color should be red")
                .isEqualTo(redColor);

        softAssert
                .assertThat(actualPasswordBorderColor)
                .as("Password border color should be red")
                .isEqualTo(redColor);

        softAssert.assertAll();
    }
}