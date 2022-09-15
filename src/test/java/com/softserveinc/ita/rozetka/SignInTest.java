package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.ConfigProperties;
import com.softserveinc.ita.rozetka.utils.LoginTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SignInTest extends LoginTestRunner {
    private final ConfigProperties property = new ConfigProperties();

    @Test
    public void verifySignInFunctionality() {
        var personalCabinetPage = homePage
                .getHeader()
                .openMyOrderPage()
                .openProfilePage();

        var softly = new SoftAssertions();

        softly.assertThat(personalCabinetPage.getUserEmail())
                .as("User name should be correct")
                .isEqualTo(property.getUserEmail());

        var isSignInModalVisible = personalCabinetPage
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
