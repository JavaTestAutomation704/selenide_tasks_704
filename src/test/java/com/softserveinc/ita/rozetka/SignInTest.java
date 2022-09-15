package com.softserveinc.ita.rozetka;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import com.softserveinc.ita.rozetka.utils.LoginTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SignInTest extends LoginTestRunner {
    private final ConfigProperties property = new ConfigProperties();

    @Test
    public void verifySignInFunctionalityWorks() {

        var profilePage = homePage
                .getHeader()
                .openMyOrdersPage()
                .openProfilePage();
        var softly = new SoftAssertions();

        softly.assertThat(profilePage.getUserEmail())
                .as("User name should be correct")
                .isEqualTo(property.getUserEmail());

        var isSignInModalVisible = profilePage
                .signOut()
                .getHeader()
                .openSignInModal()
                .isSignInModalVisible();
        softly.assertThat(isSignInModalVisible)
                .as("Sign modal should be visible")
                .isTrue();
        softly.assertAll();
    }
}
