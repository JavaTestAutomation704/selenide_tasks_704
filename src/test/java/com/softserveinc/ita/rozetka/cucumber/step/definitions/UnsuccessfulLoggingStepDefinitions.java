package com.softserveinc.ita.rozetka.cucumber.step.definitions;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import static com.softserveinc.ita.rozetka.cucumber.step.definitions.BaseStepDefinition.homePage;
import static org.assertj.core.api.Assertions.assertThat;


public class UnsuccessfulLoggingStepDefinitions {
    private final SoftAssertions softly = new SoftAssertions();
    private final Header header = homePage.getHeader();
    private LogInModal logInModal;
    private RegistrationModal registrationModal;
    private String actualEmailErrorMessage;
    private boolean isActualEmailBorderColorCorrect;

    @When("The user opens Log In modal by clicking on header user icon")
    public void theUserOpensLogInModalByClickingOnHeaderUserIcon() {
        logInModal = header.startLogging();
    }

    @Then("Log In modal should be opened")
    public void logInModalShouldBeOpened() {
        assertThat(logInModal.isOpened())
                .isTrue();
    }

    @And("Log In button should be displayed")
    public void logInButtonShouldBeDisplayed() {
        assertThat(logInModal.isLogInButtonVisible())
                .isTrue();
    }

    @And("Registration button should be displayed")
    public void registrationButtonShouldBeDisplayed() {
        assertThat(logInModal.isRegistrationButtonVisible())
                .isTrue();
    }

    @And("Remind password button should be displayed")
    public void remindPasswordButtonShouldBeDisplayed() {
        assertThat(logInModal.isRemindPasswordButtonVisible())
                .isTrue();
    }

    @When("The user tries to log in by clicking on the log in button")
    public void theUserTriesToLogInByClickingOnTheLogInButton() {
        logInModal.logIn();
    }

    @Then("Error message {string} should be displayed when submitting empty fields")
    public void errorMessageShouldBeDisplayedWhenSubmittingEmptyFields(String expectedErrorMessage) {
        actualEmailErrorMessage = logInModal.getEmailErrorMessage();
        softly.assertThat(actualEmailErrorMessage)
                .isEqualTo(expectedErrorMessage);
    }

    @And("Email border color should be {string} after submitting empty fields")
    public void emailBorderColorShouldBeRedAfterSubmittingEmptyFields(String colorRgb) {
        isActualEmailBorderColorCorrect = logInModal.isEmailBorderColorCorrect(colorRgb);
        softly.assertThat(isActualEmailBorderColorCorrect)
                .isTrue();
    }

    @And("Password border color should be {string} after submitting empty fields")
    public void passwordBorderColorShouldBeRedAfterSubmittingEmptyFields(String colorRgb) {
        var isActualPasswordBorderColorCorrect = logInModal.isPasswordBorderColorCorrect(colorRgb);
        softly.assertThat(isActualPasswordBorderColorCorrect)
                .isTrue();
    }

    @When("The user tries to remind password by clicking on the remind password button")
    public void theUserTriesToRemindPasswordByClickingOnTheRemindPasswordButton() {
        logInModal.remindPassword();
    }

    @Then("Get temporary password button should be displayed")
    public void getTemporaryPasswordButtonShouldBeDisplayed() {
        assertThat(logInModal.isGetTemporaryPasswordButtonVisible())
                .isTrue();
    }

    @And("Remember password button should be displayed")
    public void rememberPasswordButtonShouldBeDisplayed() {
        assertThat(logInModal.isRememberPasswordButtonVisible())
                .isTrue();
    }

    @When("The user tries to get temporary password by clicking on the get temporary password button")
    public void theUserTriesToGetTemporaryPasswordByClickingOnTheGetTemporaryPasswordButton() {
        logInModal.getTemporaryPassword();
    }

    @Then("Error message {string} should be displayed when submitting empty field")
    public void errorMessageShouldBeDisplayedWhenSubmittingEmptyField(String expectedErrorMessage) {
        actualEmailErrorMessage = logInModal.getEmailErrorMessage();
        softly.assertThat(actualEmailErrorMessage)
                .isEqualTo(expectedErrorMessage);
    }

    @And("Email border color should be {string} after submitting empty email field")
    public void emailBorderColorShouldBeRedAfterSubmittingEmptyEmailField(String colorRgb) {
        isActualEmailBorderColorCorrect = logInModal.isEmailBorderColorCorrect(colorRgb);
        softly.assertThat(isActualEmailBorderColorCorrect)
                .isTrue();
    }

    @When("The user remembers password by clicking on the remember password button")
    public void theUserRemembersPasswordByClickingOnTheRememberPasswordButton() {
        logInModal.rememberPassword();
    }

    @And("The user starts registration by clicking on the register button")
    public void theUserStartsRegistrationByClickingOnTheRegisterButton() {
        registrationModal = logInModal.startRegistration();
    }

    @Then("Registration modal should be opened")
    public void registrationModalShouldBeOpened() {
        softly.assertThat(registrationModal.isOpened())
                .isTrue();
    }
}