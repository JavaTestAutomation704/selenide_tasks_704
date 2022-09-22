package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.models.PersonalData;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.softserveinc.ita.rozetka.data.profile.Gender.MALE;
import static com.softserveinc.ita.rozetka.data.profile.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataTest extends LogInViaFacebookTestRunner {

    @Test
    public void verifyThatUserCanNotEditPersonalDataWithInvalidData() {
        var header = homePage.getHeader();
        header.changeLanguage(Language.UA);
        var isUaLanguageSelected = header.isLanguageSelected(Language.UA);

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

        var editPersonalDataSection = personalDataSection
                .startEditing()
                .fillInPersonalData(personalData);

        var softly = new SoftAssertions();

        var redColor = Color.RED.getRgb();
        var expectedErrorMessage = "Введіть більше 2-х символів кирилицею";

        var isActualFirstNameBorderColorCorrect = editPersonalDataSection.isFirstNameBorderColorCorrect(redColor);
        var actualFirstNameErrorMessage = editPersonalDataSection.getFirstNameErrorMessage();
        softly
                .assertThat(isActualFirstNameBorderColorCorrect)
                .as("First name border color should be red when entering first name invalid data")
                .isTrue();
        softly
                .assertThat(actualFirstNameErrorMessage)
                .as("Error message should be displayed when entering first name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualSecondNameBorderColorCorrect = editPersonalDataSection.isSecondNameBorderColorCorrect(redColor);
        var actualSecondNameErrorMessage = editPersonalDataSection.getSecondNameErrorMessage();
        softly
                .assertThat(isActualSecondNameBorderColorCorrect)
                .as("Second name border color should be red when entering second name invalid data")
                .isTrue();
        softly
                .assertThat(actualSecondNameErrorMessage)
                .as("Error message should be displayed when entering second name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualLastNameBorderColorCorrect = editPersonalDataSection.isLastNameBorderColorCorrect(redColor);
        var actualLastNameErrorMessage = editPersonalDataSection.getLastNameErrorMessage();
        softly
                .assertThat(isActualLastNameBorderColorCorrect)
                .as("Last name border color should be red when entering last name invalid data")
                .isTrue();
        softly
                .assertThat(actualLastNameErrorMessage)
                .as("Error message should be displayed when entering last name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualBirthdayBorderColorCorrect = editPersonalDataSection.isBirthdayBorderColorCorrect(redColor);
        var actualBirthdayErrorMessage = editPersonalDataSection.getBirthdayErrorMessage();
        softly
                .assertThat(isActualBirthdayBorderColorCorrect)
                .as("Birthday border color should be red when entering birthday invalid date")
                .isTrue();
        softly
                .assertThat(actualBirthdayErrorMessage)
                .as("Error message should be displayed when entering birthday invalid date")
                .isEqualTo("Введіть дату народження");

        softly.assertThat(editPersonalDataSection.isSaveButtonDisabled())
                .as("Save button should be disabled")
                .isTrue();

        softly.assertAll();
    }

    @Test
    public void verifyThatPersonalDataEditingWorksCorrectly() {
        var header = homePage.getHeader();
        header.changeLanguage(Language.UA);
        var isUaLanguageSelected = header.isLanguageSelected(Language.UA);

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

        var personalDataBeforeEditing = personalDataSection.getPersonalData();

        var editPersonalDataSection = personalDataSection.startEditing();

        var newPersonalData = PersonalData
                .builder()
                .firstName(getRandomString())
                .secondName(getRandomString())
                .lastName(getRandomString())
                .birthday(getRandomPastDate())
                .gender(MALE.getName())
                .language(UA.getName())
                .build();

        editPersonalDataSection.fillInPersonalDataIncludingSelectors(newPersonalData);

        assertThat(editPersonalDataSection.isSaveButtonDisabled())
                .as("Save button should be enabled")
                .isFalse();

        var personalDataAfterEditing = editPersonalDataSection
                .save()
                .getPersonalData();

        var softly = new SoftAssertions();

        softly.assertThat(personalDataAfterEditing)
                .as("Personal data should be updated after editing")
                .usingRecursiveComparison()
                .isNotEqualTo(personalDataBeforeEditing);

        softly.assertThat(personalDataAfterEditing)
                .as("Personal data should be the same as entered data during editing")
                .usingRecursiveComparison()
                .isEqualTo(newPersonalData);

        softly.assertAll();
    }

    private String getRandomString() {
        var alphabetUa = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        // TODO: String should have at least 2 chars, so this solves the problem when the random number is 0
        int randomNumber = new Random().nextInt(7) + 2;
        var randomString = new StringBuilder(randomNumber);

        for (int i = 0; i < randomNumber; i++) {
            int randomLetterIndex = new Random().nextInt(alphabetUa.length());
            randomString.append(alphabetUa.charAt(randomLetterIndex));
        }
        return randomString.toString();
    }

    private String getRandomPastDate() {
        int randomNumber = new Random().nextInt(30);
        // TODO: This guarantees that the date is always different
        var date = LocalDate
                .now()
                .minusYears(randomNumber)
                .plusDays(randomNumber);
        var formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formattedDate);
    }
}