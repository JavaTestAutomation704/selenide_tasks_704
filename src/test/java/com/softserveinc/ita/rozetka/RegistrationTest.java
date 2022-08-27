package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestRunner{
    @Test
    public void verifyRegistrationCapability() {

        RegistrationModal registrationModal = homePage
                .getHeader()
                .startLoggingIn()
                .startRegistration()
                .register();

        assertTrue(registrationModal.isOpen());

        SoftAssert softAssert = new SoftAssert();

        String actualFirstNameErrorMessage = registrationModal
                .getFirstNameErrorMessage();
        String actualLastNameErrorMessage = registrationModal
                .getLastNameErrorMessage();
        String actualPhoneNumberErrorMessage = registrationModal
                .getPhoneNumberErrorMessage();
        String actualEmailErrorMessage = registrationModal
                .getEmailErrorMessage();

        softAssert.assertEquals(actualFirstNameErrorMessage,
                "Введіть своє ім'я кирилицею");
        softAssert.assertEquals(actualLastNameErrorMessage,
                "Введіть своє прізвище кирилицею");
        softAssert.assertEquals(actualPhoneNumberErrorMessage,
                "Введіть номер мобільного телефону");
        softAssert.assertEquals(actualEmailErrorMessage,
                "Введіть свою ел. пошту");

        final String redColor = "rgb(248, 65, 71)";

        String actualFirstNameBorderColor = registrationModal.getFirstNameBorderColor(redColor);
        String actualLastNameBorderColor = registrationModal.getLastNameBorderColor(redColor);
        String actualPhoneNumberBorderColor = registrationModal.getPhoneNumberBorderColor(redColor);
        String actualEmailBorderColor = registrationModal.getEmailBorderColor(redColor);
        String actualPasswordBorderColor = registrationModal.getPasswordBorderColor(redColor);

        softAssert.assertEquals(actualFirstNameBorderColor, redColor);
        softAssert.assertEquals(actualLastNameBorderColor, redColor);
        softAssert.assertEquals(actualPhoneNumberBorderColor, redColor);
        softAssert.assertEquals(actualEmailBorderColor, redColor);
        softAssert.assertEquals(actualPasswordBorderColor, redColor);

        softAssert.assertAll();
    }
}