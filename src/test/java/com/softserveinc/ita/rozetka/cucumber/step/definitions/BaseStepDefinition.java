package com.softserveinc.ita.rozetka.cucumber.step.definitions;

import com.softserveinc.ita.rozetka.HomePage;
import io.cucumber.java.en.Given;

public class BaseStepDefinition {

    protected static HomePage homePage;

    @Given("home page is opened")
    public void homePageIsOpened() {
        homePage = new HomePage().open();
    }
}