package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;

public class RegistrationTest extends TestRunner {
    @Test
    public void verifyRegistrationCapability() {

        RegistrationModal registrationModal = homePage
                .getHeader()
                .startLogging()
                .startRegistration()
                .register();

        SoftAssertions softAssert = new SoftAssertions();
        boolean isUaLanguageSelected = homePage
                .getHeader()
                .isLanguageSelected(UA);

        softAssert.assertThat(isUaLanguageSelected)
                .as("Localization should in ukrainian language")
                .isTrue();

        softAssert
                .assertThat(registrationModal.isOpen())
                .as("Registration modal should be open")
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

        softAssert.assertThat(registrationModal.isOpen())
                .as("Register modal should be open")
                .isTrue();

        String redColor = Color.RED.getRgb();
        boolean actualFirstNameBorderColor = registrationModal.isFirstNameBorderColorCorrect(redColor);

        softAssert
                .assertThat(actualFirstNameBorderColor)
                .as("FirstName border color should be red")
                .isTrue();

        boolean actualLastNameBorderColor = registrationModal.isLastNameBorderColorCorrect(redColor);
        softAssert
                .assertThat(actualLastNameBorderColor)
                .as("LastName border color should be red")
                .isTrue();

        boolean actualPhoneNumberBorderColor = registrationModal.isPhoneNumberBorderColorCorrect(redColor);
        softAssert
                .assertThat(actualPhoneNumberBorderColor)
                .as("PhoneNumber border color should be red")
                .isTrue();

        boolean actualEmailBorderColor = registrationModal.isEmailBorderColorCorrect(redColor);
        softAssert
                .assertThat(actualEmailBorderColor)
                .as("Email border color should be red")
                .isTrue();

        boolean actualPasswordBorderColor = registrationModal.isPasswordBorderColorCorrect(redColor);
        softAssert
                .assertThat(actualPasswordBorderColor)
                .as("Password border color should be red")
                .isTrue();

        softAssert.assertAll();
    }
}