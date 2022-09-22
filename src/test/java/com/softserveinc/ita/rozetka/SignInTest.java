package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends BaseTestRunner {

    @Test
    public void verifyLogInAndLogOutFunctionalityWork() throws IOException {

        var header = homePage.getHeader();
        var logInModal = header.startLogging();

        assertThat(logInModal.isLogInButtonVisible())
                .as("Log in button should be visible")
                .isTrue();

        var property = new ConfigProperties();
        logInModal.logInViaFacebook(property.getFacebookUserEmailOrPhone(), property.getFacebookUserPassword());

        var softly = new SoftAssertions();
        softly.assertThat(homePage.getUserEmail())
                .as("User email should be correct")
                .isEqualTo(property.getUserEmail());
        softly.assertThat(header.isOrderIconVisible())
                .as("Order icon should be visible")
                .isTrue();

        var profilePage = header
                .openMyOrdersPage()
                .getProfileSideBar()
                .openProfilePage();

        softly.assertThat(profilePage.getUserEmail())
                .as("User email should be correct")
                .isEqualTo(property.getUserEmail());

        profilePage.logOut();

        softly.assertThat(homePage.isLogInButtonVisible())
                .as("Log in button should be visible")
                .isTrue();
        softly.assertThat(header.isHeaderLogInButtonVisible())
                .as("Log in button should be visible")
                .isTrue();
        softly.assertAll();
    }
}
