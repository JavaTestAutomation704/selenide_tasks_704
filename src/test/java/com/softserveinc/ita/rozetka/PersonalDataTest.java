package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.data.profile.CommunicationLanguage;
import com.softserveinc.ita.rozetka.data.profile.Gender;
import com.softserveinc.ita.rozetka.models.PersonalData;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.rozetka.data.ChangePasswordErrorMessage.*;
import static com.softserveinc.ita.rozetka.data.EditOrderRecipientField.*;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static com.softserveinc.ita.rozetka.utils.DateUtil.getCurrentDate;
import static com.softserveinc.ita.rozetka.utils.DateUtil.getRandomPastDate;
import static com.softserveinc.ita.rozetka.utils.RandomUtil.getRandomCyrillicString;
import static com.softserveinc.ita.rozetka.utils.RandomUtil.getRandomEnum;
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

        softly.assertThat(isActualFirstNameBorderColorCorrect)
                .as("First name border color should be red when entering first name invalid data")
                .isTrue();

        softly.assertThat(actualFirstNameErrorMessage)
                .as("Error message should be displayed when entering first name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualSecondNameBorderColorCorrect = editPersonalDataSection.isSecondNameBorderColorCorrect(redColor);
        var actualSecondNameErrorMessage = editPersonalDataSection.getSecondNameErrorMessage();

        softly.assertThat(isActualSecondNameBorderColorCorrect)
                .as("Second name border color should be red when entering second name invalid data")
                .isTrue();

        softly.assertThat(actualSecondNameErrorMessage)
                .as("Error message should be displayed when entering second name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualLastNameBorderColorCorrect = editPersonalDataSection.isLastNameBorderColorCorrect(redColor);
        var actualLastNameErrorMessage = editPersonalDataSection.getLastNameErrorMessage();

        softly.assertThat(isActualLastNameBorderColorCorrect)
                .as("Last name border color should be red when entering last name invalid data")
                .isTrue();

        softly.assertThat(actualLastNameErrorMessage)
                .as("Error message should be displayed when entering last name invalid data")
                .isEqualTo(expectedErrorMessage);

        var isActualBirthdayBorderColorCorrect = editPersonalDataSection.isBirthdayBorderColorCorrect(redColor);
        var actualBirthdayErrorMessage = editPersonalDataSection.getBirthdayErrorMessage();

        softly.assertThat(isActualBirthdayBorderColorCorrect)
                .as("Birthday border color should be red when entering birthday invalid date")
                .isTrue();

        softly.assertThat(actualBirthdayErrorMessage)
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
                .birthday(getRandomPastDate("dd-MM-yyyy"))
                .gender(getRandomEnum(Gender.class))
                .language(getRandomEnum(CommunicationLanguage.class))
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
    public void verifyThatUserCanNotChangePasswordWithInvalidData() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var passwordChangeModal = header
                .openMyOrdersPage()
                .getProfileSideBar()
                .openProfilePage()
                .startChangePassword();
        passwordChangeModal.fillInCurrentPassword("");

        var softly = new SoftAssertions();
        softly.assertThat(passwordChangeModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softly.assertThat(passwordChangeModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(INVALID_CURRENT_PASSWORD.getMessageUa());

        passwordChangeModal.fillInCurrentPassword("oldInvalidPassword");
        asList("Ab123", "invalidPassword", "invalidpassword123", "invalidPassword1@gmail.com").forEach(newPassword -> {
            passwordChangeModal
                    .fillInNewPassword(newPassword)
                    .repeatFillInNewPassword(newPassword);

            softly.assertThat(passwordChangeModal.isErrorMessageDisplayed())
                    .as("Error message should be displayed")
                    .isTrue();

            softly.assertThat(passwordChangeModal.getErrorMessageText())
                    .as("Error message should be correct")
                    .isEqualTo(INVALID_NEW_PASSWORD.getMessageUa());

            softly.assertThat(passwordChangeModal.isSaveButtonEnabled())
                    .as("Save button should be disabled")
                    .isFalse();
        });

        var validPassword = "validPassword123";
        passwordChangeModal
                .fillInNewPassword(validPassword)
                .repeatFillInNewPassword(validPassword + "1");

        softly.assertThat(passwordChangeModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softly.assertThat(passwordChangeModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(ENTERED_NEW_PASSWORDS_DO_NOT_MATCH.getMessageUa());

        softly.assertThat(passwordChangeModal.isSaveButtonEnabled())
                .as("Save button should be disabled")
                .isFalse();

        passwordChangeModal
                .fillInNewPassword(validPassword)
                .repeatFillInNewPassword(validPassword);

        softly.assertThat(passwordChangeModal.isErrorMessageDisplayed())
                .as("Error message shouldn't be displayed")
                .isFalse();

        softly.assertThat(passwordChangeModal.isSaveButtonEnabled())
                .as("Save button should be enabled")
                .isTrue();

        passwordChangeModal.saveChanges();
        //TODO When trying to save password changes, the save button may not work the first time
        passwordChangeModal.saveChanges();

        softly.assertThat(passwordChangeModal.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softly.assertThat(passwordChangeModal.getErrorMessageText())
                .as("Error message should be correct")
                .isEqualTo(INVALID_CURRENT_PASSWORD.getMessageUa());

        softly.assertThat(passwordChangeModal.isSaveButtonEnabled())
                .as("Save button should be disabled")
                .isFalse();

        softly.assertAll();
    }

    @Test
    public void verifyThatCalendarFunctionalityWorksCorrectly() {
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

        var editPersonalDataSection = personalDataSection.startEditing();

        var newPersonalData = PersonalData
                .builder()
                .firstName(getRandomCyrillicString())
                .secondName(getRandomCyrillicString())
                .lastName(getRandomCyrillicString())
                .build();

        editPersonalDataSection.fillInInputPersonalDataFields(newPersonalData);

        var calendar = editPersonalDataSection.startSelectingBirthdayDate();

        assertThat(calendar.isOpened())
                .as("Calendar should be opened")
                .isTrue();

        calendar
                .selectCurrentDate()
                .save();

        var softly = new SoftAssertions();

        var birthday = personalDataSection
                .getPersonalData()
                .getBirthday();

        // TODO: This test may be failed as current date may be not updated on the website
        softly.assertThat(birthday)
                .as("Birthday date should be current date")
                .isEqualTo(getCurrentDate("dd-MM-yyyy"));

        personalDataSection.startEditing();

        calendar = editPersonalDataSection.startSelectingBirthdayDate();
        calendar.selectRandomDate().save();

        birthday = personalDataSection
                .getPersonalData()
                .getBirthday();

        softly.assertThat(birthday)
                .as("Birthday date should be the same as selected date during editing")
                .isEqualTo(calendar.getSelectedDate());

        softly.assertAll();
    }

    @Test
    public void verifyOrderRecipientEditingWorksCorrectly() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var myOrderRecipientsSection = homePage
                .getHeader()
                .openMainSidebar()
                .openProfilePage()
                .openMyOrderRecipientsSection();
        var editOrderRecipientSection = myOrderRecipientsSection
                .startEditing()
                .startAddingRecipient();

        var softly = new SoftAssertions();
        var validPhone = "67 111 11 11";

        editOrderRecipientSection.fillIn(PHONE, validPhone);
        asList("А", "User", "").forEach(invalidName -> {
            editOrderRecipientSection
                    .fillIn(PROFILE_NAME, invalidName)
                    .fillIn(LAST_NAME, invalidName)
                    .fillIn(FIRST_NAME, invalidName)
                    .fillIn(SECOND_NAME, invalidName);
            var errorMessageList = editOrderRecipientSection.getErrorMessagesList();

            softly.assertThat(errorMessageList)
                    .as("Error message should be visible")
                    .hasSizeBetween(2, 4)
                    .as("Error message should be correct")
                    .containsOnly("Введіть більше 2-х символів кирилицею");
        });

        var validName = "Юзер";
        editOrderRecipientSection
                .fillIn(PROFILE_NAME, validName)
                .fillIn(LAST_NAME, validName)
                .fillIn(FIRST_NAME, validName)
                .fillIn(SECOND_NAME, validName)
                .fillIn(PHONE, "67");

        softly.assertThat(editOrderRecipientSection.isErrorMessageDisplayed())
                .as("Error message should be visible")
                .isTrue();

        softly.assertThat(editOrderRecipientSection.getErrorMessage())
                .as("Error message should be correct")
                .isEqualTo("Введіть телефон");

        editOrderRecipientSection.fillIn(PHONE, validPhone);

        softly.assertThat(editOrderRecipientSection.isErrorMessageDisplayed())
                .as("Error message shouldn't be displayed")
                .isFalse();

        softly.assertThat(editOrderRecipientSection.isButtonAddRecipientEnabled())
                .as("Button order recipient should be enabled")
                .isTrue();

        editOrderRecipientSection.addRecipient();

        softly.assertThat(myOrderRecipientsSection.getRecipientName())
                .as("Recipient name should be correct")
                .isEqualTo(validName);

        softly.assertThat(myOrderRecipientsSection.getRecipientPhone())
                .as("Recipient phone should be correct")
                .isEqualTo("+38 0" + validPhone);

        //TODO if you open the editing component, the delete icon may not be displayed, so you need to reload the page first
        refresh();
        myOrderRecipientsSection
                .startEditing()
                .removeRecipient()
                .saveChanges();

        softly.assertThat(myOrderRecipientsSection.isRecipientNameDisplayed())
                .as("Recipient name shouldn't be displayed")
                .isFalse();

        softly.assertAll();
    }
}