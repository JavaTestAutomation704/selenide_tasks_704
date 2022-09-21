package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.models.PersonalData;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataTest extends LogInViaFacebookTestRunner {

    @Test
    public void verifyThatUserCanNotEditPersonalDataWithInvalidData() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var personalDataSection = homePage
                .getHeader()
                .openMainSidebar()
                .openProfilePage()
                .openPersonalDataSection();

        assertThat(personalDataSection.isOpened())
                .as("Personal data section should be opened")
                .isTrue();

        var personalData = PersonalData
                .builder()
                .firstName("ww")
                .secondName("rr")
                .lastName("ll")
                .birthday("0")
                .build();

        personalDataSection
                .startEditing()
                .fillInPersonalData(personalData);

        var softly = new SoftAssertions();

        var redColor = Color.RED.getRgb();
        var expectedErrorMessage = "Введіть більше 2-х символів кирилицею";

        var isActualFirstNameBorderColorCorrect = personalDataSection.isFirstNameBorderColorCorrect(redColor);
        var actualFirstNameErrorMessage = personalDataSection.getFirstNameErrorMessage();
        softly
                .assertThat(isActualFirstNameBorderColorCorrect)
                .as("First name border color should be red when entering first name invalid data")
                .isTrue();
        softly
                .assertThat(actualFirstNameErrorMessage)
                .as("Error message should be displayed when entering first name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualSecondNameBorderColorCorrect = personalDataSection.isSecondNameBorderColorCorrect(redColor);
        var actualSecondNameErrorMessage = personalDataSection.getSecondNameErrorMessage();
        softly
                .assertThat(isActualSecondNameBorderColorCorrect)
                .as("Second name border color should be red when entering second name invalid data")
                .isTrue();
        softly
                .assertThat(actualSecondNameErrorMessage)
                .as("Error message should be displayed when entering second name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualLastNameBorderColorCorrect = personalDataSection.isLastNameBorderColorCorrect(redColor);
        var actualLastNameErrorMessage = personalDataSection.getLastNameErrorMessage();
        softly
                .assertThat(isActualLastNameBorderColorCorrect)
                .as("Last name border color should be red when entering last name invalid data")
                .isTrue();
        softly
                .assertThat(actualLastNameErrorMessage)
                .as("Error message should be displayed when entering last name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualBirthdayBorderColorCorrect = personalDataSection.isBirthdayBorderColorCorrect(redColor);
        var actualBirthdayErrorMessage = personalDataSection.getBirthdayErrorMessage();
        softly
                .assertThat(isActualBirthdayBorderColorCorrect)
                .as("Birthday border color should be red when entering birthday invalid date")
                .isTrue();
        softly
                .assertThat(actualBirthdayErrorMessage)
                .as("Error message should be displayed when entering birthday invalid date")
                .isEqualTo("Введіть дату народження");

        softly.assertThat(personalDataSection.isSaveButtonDisabled())
                .as("Save button should be disabled")
                .isTrue();

        softly.assertAll();
    }
}