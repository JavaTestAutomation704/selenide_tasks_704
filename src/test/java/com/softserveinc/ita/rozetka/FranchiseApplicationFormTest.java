package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.FranchiseFormField.*;
import static com.softserveinc.ita.rozetka.data.Language.UA;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class FranchiseApplicationFormTest extends TestRunner {

    @BeforeMethod
    public void resetLanguage() {
        var header = homePage.getHeader();
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }
    }

    @Test
    public void verifyErrorMessagesAreShownForEmptyInput() {
        var franchiseApplicationForm = homePage
                .getPartnerSection()
                .openFranchisePage()
                .getFranchiseApplicationForm();

        var fields = asList(NAME, PHONE, EMAIL, CITY, EXPERIENCE, MOTIVATION);
        fields.forEach(field -> franchiseApplicationForm.fillIn(field, ""));

        franchiseApplicationForm.fillIn(YOUTUBE, ""); //select field that is not required

        var softly = new SoftAssertions();
        fields.forEach(field -> softly.assertThat(franchiseApplicationForm.isErrorVisible(field))
                .as(field.getFieldName() + " error should be visible")
                .isTrue()
        );

        var invalidErrorTemplate = "Invalid error for empty input into '%s' field";
        softly.assertThat(franchiseApplicationForm.getError(NAME))
                .as(format(invalidErrorTemplate, NAME.getFieldName()))
                .isEqualTo("Введіть ім'я та прізвище");
        softly.assertThat(franchiseApplicationForm.getError(PHONE))
                .as(format(invalidErrorTemplate, PHONE.getFieldName()))
                .isEqualTo("Введіть номер телефону");
        softly.assertThat(franchiseApplicationForm.getError(EMAIL))
                .as(format(invalidErrorTemplate, EMAIL.getFieldName()))
                .isEqualTo("Введіть ел. пошту");
        softly.assertThat(franchiseApplicationForm.getError(CITY))
                .as(format(invalidErrorTemplate, CITY.getFieldName()))
                .isEqualTo("Введіть назву міста");
        softly.assertThat(franchiseApplicationForm.getError(EXPERIENCE))
                .as(format(invalidErrorTemplate, EXPERIENCE.getFieldName()))
                .isEqualTo("Це поле обов'язкове");
        softly.assertThat(franchiseApplicationForm.getError(MOTIVATION))
                .as(format(invalidErrorTemplate, MOTIVATION.getFieldName()))
                .isEqualTo("Це поле обов'язкове");

        softly.assertThat(franchiseApplicationForm.isSubmitButtonEnabled())
                .as("Submit form button should be disabled")
                .isFalse();
        softly.assertAll();
    }

    @Test
    public void verifyErrorMessagesAreShownForInvalidInput() {
        var franchiseApplicationForm = homePage
                .getPartnerSection()
                .openFranchisePage()
                .getFranchiseApplicationForm()
                .fillIn(NAME, "some name")
                .fillIn(PHONE, "7")
                .fillIn(EMAIL, "some")
                .fillIn(CITY, "Київ")
                .fillIn(EXPERIENCE, "some")
                .fillIn(MOTIVATION, "some");

        var softly = new SoftAssertions();
        var fields = asList(NAME, PHONE, EMAIL, CITY);
        fields.forEach(field -> softly.assertThat(franchiseApplicationForm.isErrorVisible(field))
                .as(field.getFieldName() + " error should be visible")
                //TODO: Phone error does not appear
                .isTrue()
        );

        var invalidErrorTemplate = "Incorrect error for invalid input into '%s' field";
        softly.assertThat(franchiseApplicationForm.getError(NAME))
                .as(format(invalidErrorTemplate, NAME.getFieldName()))
                .isEqualTo("Введіть ім'я та прізвище на кирилиці");

        softly.assertThat(franchiseApplicationForm.getError(PHONE))
                .as(format(invalidErrorTemplate, PHONE.getFieldName()))
                //TODO: Phone error does not appear
                .isEqualTo("Введіть коректний номер телефону");
        softly.assertThat(franchiseApplicationForm.getError(EMAIL))
                .as(format(invalidErrorTemplate, EMAIL.getFieldName()))
                .isEqualTo("Введіть коректну адресу ел. пошти");
        softly.assertThat(franchiseApplicationForm.getError(CITY))
                .as(format(invalidErrorTemplate, CITY.getFieldName()))
                .isEqualTo("Оберіть місто зі списку");

        softly.assertThat(franchiseApplicationForm.isSubmitButtonEnabled())
                .as("Submit form button should be disabled")
                .isFalse();
        softly.assertAll();
    }

    @Test
    public void verifySubmitButtonIsEnabledForValidInput() {
        var franchiseApplicationForm = homePage
                .getPartnerSection()
                .openFranchisePage()
                .getFranchiseApplicationForm()
                .fillIn(NAME, "Якесь ім'я")
                .fillIn(PHONE, "380639521878")
                .fillIn(EMAIL, "some@gmail.com")
                .fillIn(EXPERIENCE, "Досвітчений")
                .fillIn(MOTIVATION, "Модно")
                .fillIn(CITY, "Київ")
                .selectCity(1);

        var softly = new SoftAssertions();
        var fields = asList(NAME, PHONE, EMAIL, CITY, EXPERIENCE, MOTIVATION);
        fields.forEach(field -> softly.assertThat(franchiseApplicationForm.isErrorVisible(field))
                .as(field.getFieldName() + " error should not be visible")
                .isFalse()
        );

        softly.assertThat(franchiseApplicationForm.isSubmitButtonEnabled())
                .as("Submit form button should be enabled")
                .isTrue();
        softly.assertAll();
    }
}