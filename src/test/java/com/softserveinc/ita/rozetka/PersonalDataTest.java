package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.models.PersonalData;
import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.softserveinc.ita.rozetka.data.ErrorMessage.*;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static com.softserveinc.ita.rozetka.data.profile.CommunicationLanguage.UKRAINIAN;
import static com.softserveinc.ita.rozetka.data.profile.Gender.MALE;
import static com.softserveinc.ita.rozetka.utils.DateUtil.getRandomPastDate;
import static com.softserveinc.ita.rozetka.utils.RandomUtil.getRandomCyrillicString;
import static java.util.Arrays.asList;
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

        var personalDataSection = header
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
                .fillInInputPersonalDataFields(personalData);

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
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var personalDataSection = header
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
                .firstName(getRandomCyrillicString())
                .secondName(getRandomCyrillicString())
                .lastName(getRandomCyrillicString())
                .birthday(getRandomPastDate())
                .gender(MALE)
                .language(UKRAINIAN)
                .build();

        editPersonalDataSection.fillInAllPersonalDataFields(newPersonalData);

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

    @Test
    public void verifyThatUserCanNotEditPasswordWithInvalidData() throws IOException {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var passwordEditingModal = header
                .openMyOrdersPage()
                .getProfileSideBar()
                .openProfilePage()
                .startEditPassword();
        passwordEditingModal.fillInCurrentPassword("");

        var softly = new SoftAssertions();
        softly.assertThat(passwordEditingModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        softly.assertThat(passwordEditingModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(INVALID_CURRENT_PASSWORD.getMessageUa());

        var configProperties = new ConfigProperties();
        passwordEditingModal.fillInCurrentPassword(configProperties.getFacebookUserPassword());

        asList("Ab123", "invalidPassword", "invalidpassword123", configProperties.getUserEmail()).forEach(newPassword -> {
            passwordEditingModal
                    .fillInNewPassword(newPassword)
                    .repeatFillInNewPassword(newPassword);

            softly.assertThat(passwordEditingModal.isErrorMessageDisplayed())
                    .as("Error message should be displayed")
                    .isTrue();
            softly.assertThat(passwordEditingModal.getErrorMessageText())
                    .as("Error message should be correct")
                    .isEqualTo(INVALID_NEW_PASSWORD.getMessageUa());
            softly.assertThat(passwordEditingModal.isSaveButtonEnabled())
                    .as("Save button should be disabled")
                    .isFalse();
        });

        var validPassword = "validPassword123";
        passwordEditingModal
                .fillInNewPassword(validPassword)
                .repeatFillInNewPassword(validPassword + "1");

        softly.assertThat(passwordEditingModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        softly.assertThat(passwordEditingModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(ENTERED_NEW_PASSWORDS_DO_NOT_MATCH.getMessageUa());
        softly.assertThat(passwordEditingModal.isSaveButtonEnabled())
                .as("Save button should be disabled")
                .isFalse();

        passwordEditingModal
                .fillInCurrentPassword("invalidPassword")
                .fillInNewPassword(validPassword)
                .repeatFillInNewPassword(validPassword);

        softly.assertThat(passwordEditingModal.isErrorMessageDisplayed())
                .as("Error message shouldn't be displayed")
                .isFalse();
        softly.assertThat(passwordEditingModal.isSaveButtonEnabled())
                .as("Save button should be enabled")
                .isTrue();

        passwordEditingModal.saveChanges();
        //TODO When trying to save password changes, the save button may not work the first time
        passwordEditingModal.saveChanges();

        softly.assertThat(passwordEditingModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        softly.assertThat(passwordEditingModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(INVALID_CURRENT_PASSWORD.getMessageUa());
        softly.assertThat(passwordEditingModal.isSaveButtonEnabled())
                .as("Save button should be disabled")
                .isFalse();
        softly.assertAll();
    }
}