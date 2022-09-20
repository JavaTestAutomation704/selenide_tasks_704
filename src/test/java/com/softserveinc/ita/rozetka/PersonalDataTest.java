package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataTest extends LogInViaFacebookTestRunner {

    @Test
    public void verifyThatUserCanNotEditPersonalDataWithInvalidData() {
        var personalDataSection = homePage
                .getHeader()
                .openMainSidebar()
                .openProfilePage()
                .openPersonalDataSection();

        assertThat(personalDataSection.isOpened())
                .as("Personal data section should be opened")
                .isTrue();

        personalDataSection
                .edit()
                .fillInPersonalData("ww", "rr", "ll", "0");

        var softly = new SoftAssertions();

        var redColor = Color.RED.getRgb();
        var expectedErrorMessage = "Введіть більше 2-х символів кирилицею";

        var isActualFirstNameBorderColorCorrect = personalDataSection.isFirstNameBorderColorCorrect(redColor);
        var actualFirstNameErrorMessage = personalDataSection.getFirstNameErrorMessage();
        softly
                .assertThat(isActualFirstNameBorderColorCorrect)
                .as("First name border color should be red " +
                        "when entering first name invalid data on the personal data section")
                .isTrue();
        softly
                .assertThat(actualFirstNameErrorMessage)
                .as("Error message should be displayed " +
                        "when entering first name invalid data on the personal data section")
                .isEqualTo(expectedErrorMessage);

        var isActualSecondNameBorderColorCorrect = personalDataSection.isSecondNameBorderColorCorrect(redColor);
        var actualSecondNameErrorMessage = personalDataSection.getSecondNameErrorMessage();
        softly
                .assertThat(isActualSecondNameBorderColorCorrect)
                .as("Second name border color should be red " +
                        "when entering second name invalid data on the personal data section")
                .isTrue();
        softly
                .assertThat(actualSecondNameErrorMessage)
                .as("Error message should be displayed " +
                        "when entering second name invalid data on the personal data section")
                .isEqualTo(expectedErrorMessage);

        var isActualLastNameBorderColorCorrect = personalDataSection.isLastNameBorderColorCorrect(redColor);
        var actualLastNameErrorMessage = personalDataSection.getLastNameErrorMessage();
        softly
                .assertThat(isActualLastNameBorderColorCorrect)
                .as("Last name border color should be red " +
                        "when entering last name invalid data on the personal data section")
                .isTrue();
        softly
                .assertThat(actualLastNameErrorMessage)
                .as("Error message should be displayed " +
                        "when entering last name invalid data on the personal data section")
                .isEqualTo(expectedErrorMessage);

        var isActualBirthdayBorderColorCorrect = personalDataSection.isBirthdayBorderColorCorrect(redColor);
        var actualBirthdayErrorMessage = personalDataSection.getBirthdayErrorMessage();
        softly
                .assertThat(isActualBirthdayBorderColorCorrect)
                .as("Birthday border color should be red " +
                        "when entering birthday invalid date on the personal data section")
                .isTrue();
        softly
                .assertThat(actualBirthdayErrorMessage)
                .as("Error message should be displayed " +
                        "when entering birthday invalid date on the personal data section")
                .isEqualTo("Введіть дату народження");

        softly.assertThat(personalDataSection.isSaveButtonDisabled())
                .as("Save button should be disabled")
                .isTrue();

        softly.assertAll();
    }
}