package com.softserveinc.ita.rozetka.cucumber.step.definitions;

import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import io.cucumber.java.en.When;

import java.io.IOException;

public class LoggingStepDefinition {

    @When("The user logs in")
    public void theUserLogsIn() throws IOException {
        var property = new ConfigProperties();
        BaseStepDefinition
                .homePage
                .getHeader()
                .startLogging()
                .logInViaFacebook(property.getFacebookUserEmailOrPhone(), property.getFacebookUserPassword());
    }
}