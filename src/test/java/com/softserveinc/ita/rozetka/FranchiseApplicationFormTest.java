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

        var fields = asList(NAME, PHONE, EMAIL, CITY, EXPERIENCE, MOTIVATION, YOUTUBE);

        fields.forEach(field -> franchiseApplicationForm.enter(field, ""));

        var softly = new SoftAssertions();
        fields
                .subList(0, fields.size() - 1)
                .forEach(field -> softly.assertThat(franchiseApplicationForm.isErrorVisible(field))
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

        softly.assertThat(franchiseApplicationForm.isSubmitButtonDisabled())
                .as("Submit form button should be disabled")
                .isTrue();
        softly.assertAll();
    }
}