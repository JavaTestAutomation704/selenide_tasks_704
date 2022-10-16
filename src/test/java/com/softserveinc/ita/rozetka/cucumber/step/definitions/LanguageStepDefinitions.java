package com.softserveinc.ita.rozetka.cucumber.step.definitions;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.Language;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.softserveinc.ita.rozetka.cucumber.step.definitions.BaseStepDefinition.homePage;
import static org.assertj.core.api.Assertions.assertThat;

public class LanguageStepDefinitions {

    private final Header header = homePage.getHeader();

    @When("The user changes language to {string}")
    public void theUserChangesLanguageToUA(String language) {
        header.changeLanguage(Language.getByValue(language));
    }

    @Then("Localization should be switched to {string}")
    public void localizationShouldBeSwitchedToUA(String language) {
        var isUaLanguageSelected = header.isLanguageSelected(Language.getByValue(language));
        assertThat(isUaLanguageSelected).isTrue();
    }
}