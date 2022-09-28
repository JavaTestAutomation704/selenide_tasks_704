package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;

public class RegistrationTest extends BaseTestRunner {
    @Test
    public void verifyThatBordersAreRedAndErrorMessageAppearsAfterLeavingEmptyFields() {

        var registrationModal = homePage
                .getHeader()
                .startLogging()
                .startRegistration()
                .register();

        var softly = new SoftAssertions();
        var isUaLanguageSelected = homePage
                .getHeader()
                .isLanguageSelected(UA);

        softly.assertThat(isUaLanguageSelected)
                .as("Localization should be in ukrainian language")
                .isTrue();

        softly.assertThat(registrationModal.isOpened())
                .as("Registration modal should be opened")
                .isTrue();

        var actualFirstNameErrorMessage = registrationModal.getFirstNameErrorMessage();
        softly.assertThat(actualFirstNameErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть своє ім'я кирилицею");

        var actualLastNameErrorMessage = registrationModal.getLastNameErrorMessage();
        softly.assertThat(actualLastNameErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть своє прізвище кирилицею");

        var actualPhoneNumberErrorMessage = registrationModal.getPhoneNumberErrorMessage();
        softly.assertThat(actualPhoneNumberErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть номер мобільного телефону");

        var actualEmailErrorMessage = registrationModal.getEmailErrorMessage();
        softly.assertThat(actualEmailErrorMessage)
                .as("Error message doesn't appear")
                .isEqualTo("Введіть свою ел. пошту");

        softly.assertThat(registrationModal.isOpened())
                .as("Register modal should be opened")
                .isTrue();

        var redColor = Color.RED.getRgb();
        var isActualFirstNameBorderInCorrectColor = registrationModal.isFirstNameBorderColorCorrect(redColor);

        softly.assertThat(isActualFirstNameBorderInCorrectColor)
                .as("FirstName border color should be red")
                .isTrue();

        var isActualLastNameBorderInCorrectColor = registrationModal.isLastNameBorderColorCorrect(redColor);
        softly.assertThat(isActualLastNameBorderInCorrectColor)
                .as("LastName border color should be red")
                .isTrue();

        var isPhoneNumberBorderInCorrectColor = registrationModal.isPhoneNumberBorderColorCorrect(redColor);
        softly.assertThat(isPhoneNumberBorderInCorrectColor)
                .as("PhoneNumber border color should be red")
                .isTrue();

        var isEmailBorderInCorrectColor = registrationModal.isEmailBorderColorCorrect(redColor);
        softly.assertThat(isEmailBorderInCorrectColor)
                .as("Email border color should be red")
                .isTrue();

        var isPasswordBorderInCorrectColor = registrationModal.isPasswordBorderColorCorrect(redColor);
        softly.assertThat(isPasswordBorderInCorrectColor)
                .as("Password border color should be red")
                .isTrue();

        softly.assertAll();
    }
}