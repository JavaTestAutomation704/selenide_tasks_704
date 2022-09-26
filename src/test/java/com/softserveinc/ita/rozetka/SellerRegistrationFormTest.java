package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.seller.registration.form.StepOneSellerRegistrationForm;
import com.softserveinc.ita.rozetka.models.Seller;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class SellerRegistrationFormTest extends BaseTestRunner {

    private StepOneSellerRegistrationForm openSellerRegistrationPage() {
        var header = homePage.getHeader();
        if (!header.isLanguageSelected(UA)) {
            header
                    .openMainSidebar()
                    .changeLanguage(UA);
        }

        var sellOnRozetkaPage = homePage
                .getPartnerSection()
                .openSellOnRozetkaPage();

        assertThat(sellOnRozetkaPage.isOpened())
                .as("Sell on rozetka page should be opened")
                .isTrue();

        var sellerRegistrationPage = sellOnRozetkaPage.openSellerRegistrationPage();

        assertThat(sellerRegistrationPage.isOpened())
                .as("Seller registration page should be opened")
                .isTrue();

        return sellerRegistrationPage.getStepOneSellerRegistrationForm();
    }

    @Test
    public void verifyApplicationCanNotBeSubmittedAndErrorMessagesAppearWhenSellerRegistrationFormFieldsAreEmpty() {
        var stepOneSellerRegistrationForm = openSellerRegistrationPage();

        assertThat(stepOneSellerRegistrationForm.isOpened())
                .as("Seller registration form step one should be opened")
                .isTrue();

        stepOneSellerRegistrationForm.clearAllFields();

        var expectedErrorMessage = "Обов'язкове поле";
        var actualShopNameFieldErrorMessage = stepOneSellerRegistrationForm.getShopNameFieldErrorMessage();

        var softly = new SoftAssertions();
        softly.assertThat(actualShopNameFieldErrorMessage)
                .as("Error message should appear when shop name field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualSiteUrlFieldErrorMessage = stepOneSellerRegistrationForm.getSiteUrlFieldErrorMessage();

        softly.assertThat(actualSiteUrlFieldErrorMessage)
                .as("Error message should appear when site url field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualProductsAmountFieldErrorMessage = stepOneSellerRegistrationForm.getProductsAmountFieldErrorMessage();

        softly.assertThat(actualProductsAmountFieldErrorMessage)
                .as("Error message should appear when products amount field is empty")
                .isEqualTo(expectedErrorMessage);

        var stepTwoSellerRegistrationForm = stepOneSellerRegistrationForm.openStepTwoSellerRegistrationForm();

        assertThat(stepTwoSellerRegistrationForm.isOpened())
                .as("Step two seller registration form should be opened")
                .isTrue();

        stepTwoSellerRegistrationForm.clearAllFields();

        var actualFullNameFieldErrorMessage = stepTwoSellerRegistrationForm.getFullNameFieldErrorMessage();

        softly.assertThat(actualFullNameFieldErrorMessage)
                .as("Error message should appear when full name field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualPositionFieldErrorMessage = stepTwoSellerRegistrationForm.getPositionFieldErrorMessage();

        softly.assertThat(actualPositionFieldErrorMessage)
                .as("Error message should appear when position field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualEmailFieldErrorMessage = stepTwoSellerRegistrationForm.getEmailFieldErrorMessage();

        softly.assertThat(actualEmailFieldErrorMessage)
                .as("Error message should appear when email field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualPhoneNumberFieldErrorMessage = stepTwoSellerRegistrationForm.getPhoneNumberFieldErrorMessage();

        softly.assertThat(actualPhoneNumberFieldErrorMessage)
                .as("Error message should appear when phone number field is empty")
                .isEqualTo(expectedErrorMessage);

        softly.assertThat(stepTwoSellerRegistrationForm.isRegistrationButtonDisabled())
                .as("Registration button should be disabled")
                .isTrue();

        softly.assertAll();
    }

    @Test
    public void verifyErrorMessagesAppearWhenSellerRegistrationFormFieldsAreFilledInWithInvalidData() {
        var stepOneSellerRegistrationForm = openSellerRegistrationPage();

        assertThat(stepOneSellerRegistrationForm.isOpened())
                .as("Step one seller registration form should be opened")
                .isTrue();

        var invalidFieldInput = " ";

        var seller = Seller.builder()
                .shopName(invalidFieldInput)
                .siteUrl("new address")
                .productsAmount("ten products")
                .fullName(invalidFieldInput)
                .position(invalidFieldInput)
                .email("email")
                .phoneNumber("0000")
                .build();

        stepOneSellerRegistrationForm.fillInShopInformation(seller);

        var expectedErrorMessage = "Значення не відповідає формату";
        var actualShopNameFieldErrorMessage = stepOneSellerRegistrationForm.getShopNameFieldErrorMessage();

        var softly = new SoftAssertions();
        softly.assertThat(actualShopNameFieldErrorMessage)
                .as("Error message should appear when shop name field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualSiteUrlFieldErrorMessage = stepOneSellerRegistrationForm.getSiteUrlFieldErrorMessage();

        softly.assertThat(actualSiteUrlFieldErrorMessage)
                .as("Error message should appear when site url field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualProductsAmountFieldErrorMessage = stepOneSellerRegistrationForm.getProductsAmountFieldErrorMessage();

        softly.assertThat(actualProductsAmountFieldErrorMessage)
                .as("Error message should appear when products amount field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var stepTwoSellerRegistrationForm = stepOneSellerRegistrationForm.openStepTwoSellerRegistrationForm();

        assertThat(stepTwoSellerRegistrationForm.isOpened())
                .as("Step two seller registration form should be opened")
                .isTrue();

        stepTwoSellerRegistrationForm.fillInContactInformation(seller);

        var actualFullNameFieldErrorMessage = stepTwoSellerRegistrationForm.getFullNameFieldErrorMessage();

        softly.assertThat(actualFullNameFieldErrorMessage)
                .as("Error message should appear when full name field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualPositionFieldErrorMessage = stepTwoSellerRegistrationForm.getPositionFieldErrorMessage();

        softly.assertThat(actualPositionFieldErrorMessage)
                .as("Error message should appear when position field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualEmailFieldErrorMessage = stepTwoSellerRegistrationForm.getEmailFieldErrorMessage();

        softly.assertThat(actualEmailFieldErrorMessage)
                .as("Error message should appear when email field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualPhoneNumberFieldErrorMessage = stepTwoSellerRegistrationForm.getPhoneNumberFieldErrorMessage();

        softly.assertThat(actualPhoneNumberFieldErrorMessage)
                .as("Error message should appear when phone number field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);
        //TODO: this test will fail as shopName and title fields error messages don't appeared
        // when these fields are fill in with invalid data
        softly.assertAll();
    }

    @Test
    public void verifyDataIsRememberedWhenUserNavigatesViaSellerRegistrationForm() {
        var stepOneSellerRegistrationForm = openSellerRegistrationPage();

        assertThat(stepOneSellerRegistrationForm.isOpened())
                .as("Step one seller registration form should be opened")
                .isTrue();

        var seller = Seller.builder()
                .shopName("Starbucks shop")
                .siteUrl("www.starbucks.com/")
                .productsAmount("10000")
                .fullName("Прізвище Ім'я Тест")
                .position("Manager")
                .email("oldAddress@gmail.com")
                .phoneNumber("632299457")
                .build();

        var stepTwoSellerRegistrationForm = stepOneSellerRegistrationForm
                .fillInShopInformation(seller)
                .continueToStepTwoSellerRegistrationForm();

        assertThat(stepTwoSellerRegistrationForm.isOpened())
                .as("Step two seller registration form should be opened")
                .isTrue();

        stepTwoSellerRegistrationForm.fillInContactInformation(seller);

        var softly = new SoftAssertions();
        softly.assertThat(stepTwoSellerRegistrationForm.isRegistrationButtonDisabled())
                .as("Registration button should be enabled")
                .isFalse();

        stepTwoSellerRegistrationForm.backToStepOneSellerRegistrationForm();

        assertThat(stepOneSellerRegistrationForm.isOpened())
                .as("Step one seller registration form should be opened")
                .isTrue();

        var actualSeller = Seller.builder()
                .shopName(stepOneSellerRegistrationForm.getShopName())
                .siteUrl(stepOneSellerRegistrationForm.getSiteUrl())
                .productsAmount(stepOneSellerRegistrationForm.getProductsAmount());

        stepOneSellerRegistrationForm.openStepTwoSellerRegistrationForm();

        assertThat(stepTwoSellerRegistrationForm.isOpened())
                .as("Step two seller registration form should be opened")
                .isTrue();

        actualSeller
                .fullName(stepTwoSellerRegistrationForm.getFullName())
                .position(stepTwoSellerRegistrationForm.getPosition())
                .email(stepTwoSellerRegistrationForm.getEmail())
                .phoneNumber(stepTwoSellerRegistrationForm.getPhoneNumber())
                .build();

        softly.assertThat(seller)
                .as("Seller information should be remembered")
                .usingRecursiveComparison()
                .isEqualTo(actualSeller);

        stepTwoSellerRegistrationForm.openStepOneSellerRegistrationForm();

        softly.assertThat(stepOneSellerRegistrationForm.isOpened())
                .as("Step one seller registration form should be opened")
                .isTrue();

        softly.assertAll();
    }
}