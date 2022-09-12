package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.seller.registration.form.StepOneSellerRegistrationForm;
import com.softserveinc.ita.rozetka.models.Seller;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class SellerRegistrationFormTest extends TestRunner {

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
        var sellerRegistrationFormStepOne = openSellerRegistrationPage();

        assertThat(sellerRegistrationFormStepOne.isOpened())
                .as("Seller registration form step one should be opened")
                .isTrue();

        sellerRegistrationFormStepOne.clearAllFields();

        var expectedErrorMessage = "Обов'язкове поле";
        var actualShopNameFieldErrorMessage = sellerRegistrationFormStepOne.getShopNameFieldErrorMessage();

        var softly = new SoftAssertions();
        softly.assertThat(actualShopNameFieldErrorMessage)
                .as("Error message should appear when shop name field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualSiteUrlFieldErrorMessage = sellerRegistrationFormStepOne.getSiteUrlFieldErrorMessage();

        softly.assertThat(actualSiteUrlFieldErrorMessage)
                .as("Error message should appear when site url field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualProductsAmountFieldErrorMessage = sellerRegistrationFormStepOne.getProductsAmountFieldErrorMessage();

        softly.assertThat(actualProductsAmountFieldErrorMessage)
                .as("Error message should appear when products amount field is empty")
                .isEqualTo(expectedErrorMessage);

        var sellerRegistrationFormStepTwo = sellerRegistrationFormStepOne.openStepTwoSellerRegistrationFormViaTabPanel();

        assertThat(sellerRegistrationFormStepTwo.isOpened())
                .as("Seller registration form step two should be opened")
                .isTrue();

        sellerRegistrationFormStepTwo.clearAllFields();

        var actualFullNameFieldErrorMessage = sellerRegistrationFormStepTwo.getFullNameFieldErrorMessage();

        softly.assertThat(actualFullNameFieldErrorMessage)
                .as("Error message should appear when full name field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualPositionFieldErrorMessage = sellerRegistrationFormStepTwo.getPositionFieldErrorMessage();

        softly.assertThat(actualPositionFieldErrorMessage)
                .as("Error message should appear when position field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualEmailFieldErrorMessage = sellerRegistrationFormStepTwo.getEmailFieldErrorMessage();

        softly.assertThat(actualEmailFieldErrorMessage)
                .as("Error message should appear when email field is empty")
                .isEqualTo(expectedErrorMessage);

        var actualPhoneNumberFieldErrorMessage = sellerRegistrationFormStepTwo.getPhoneNumberFieldErrorMessage();

        softly.assertThat(actualPhoneNumberFieldErrorMessage)
                .as("Error message should appear when phone number field is empty")
                .isEqualTo(expectedErrorMessage);

        softly.assertThat(sellerRegistrationFormStepTwo.isRegistrationButtonDisabled())
                .as("Registration button should be disabled")
                .isTrue();

        softly.assertAll();
    }

    @Test
    public void verifyErrorMessagesAppearWhenSellerRegistrationFormFieldsAreFilledInWithInvalidData() {
        var sellerRegistrationFormStepOne = openSellerRegistrationPage();

        assertThat(sellerRegistrationFormStepOne.isOpened())
                .as("Seller registration form step one should be opened")
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

        sellerRegistrationFormStepOne.fillInShopInformation(seller);

        var expectedErrorMessage = "Значення не відповідає формату";
        var actualShopNameFieldErrorMessage = sellerRegistrationFormStepOne.getShopNameFieldErrorMessage();

        var softly = new SoftAssertions();
        softly.assertThat(actualShopNameFieldErrorMessage)
                .as("Error message should appear when shop name field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualSiteUrlFieldErrorMessage = sellerRegistrationFormStepOne.getSiteUrlFieldErrorMessage();

        softly.assertThat(actualSiteUrlFieldErrorMessage)
                .as("Error message should appear when site url field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualProductsAmountFieldErrorMessage = sellerRegistrationFormStepOne.getProductsAmountFieldErrorMessage();

        softly.assertThat(actualProductsAmountFieldErrorMessage)
                .as("Error message should appear when products amount field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var sellerRegistrationFormStepTwo = sellerRegistrationFormStepOne.openStepTwoSellerRegistrationFormViaTabPanel();

        assertThat(sellerRegistrationFormStepTwo.isOpened())
                .as("Seller registration form step two should be opened")
                .isTrue();

        sellerRegistrationFormStepTwo.fillInContactInformation(seller);

        var actualFullNameFieldErrorMessage = sellerRegistrationFormStepTwo.getFullNameFieldErrorMessage();

        softly.assertThat(actualFullNameFieldErrorMessage)
                .as("Error message should appear when full name field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualPositionFieldErrorMessage = sellerRegistrationFormStepTwo.getPositionFieldErrorMessage();

        softly.assertThat(actualPositionFieldErrorMessage)
                .as("Error message should appear when position field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualEmailFieldErrorMessage = sellerRegistrationFormStepTwo.getEmailFieldErrorMessage();

        softly.assertThat(actualEmailFieldErrorMessage)
                .as("Error message should appear when email field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);

        var actualPhoneNumberFieldErrorMessage = sellerRegistrationFormStepTwo.getPhoneNumberFieldErrorMessage();

        softly.assertThat(actualPhoneNumberFieldErrorMessage)
                .as("Error message should appear when phone number field is filled in with invalid data")
                .isEqualTo(expectedErrorMessage);
        //TODO: this test will fail as shopName and title fields error messages don't appeared
        // when these fields are fill in with invalid data
        softly.assertAll();
    }
}