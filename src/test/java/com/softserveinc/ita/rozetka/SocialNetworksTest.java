package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static java.lang.String.format;

public class SocialNetworksTest extends TestRunner {

    @Test
    public void verifyThatUserCanOpenRozetkaSocialNetworksPages() {
        var socialNetworkPages = homePage.getAvailableRozetkaSocialNetworksPages();

        var softAssertions = new SoftAssertions();

        socialNetworkPages.forEach(socialNetworkPage -> {
            socialNetworkPage.open();
            softAssertions
                    .assertThat(socialNetworkPage.isOpened())
                    .as(format("Rozetka %s page should be opened", socialNetworkPage.getName()))
                    .isTrue();
            homePage.backToHomePageTab();
        });

        softAssertions.assertAll();
    }
}