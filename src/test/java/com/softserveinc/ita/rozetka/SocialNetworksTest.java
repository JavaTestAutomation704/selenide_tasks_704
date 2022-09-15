package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SocialNetworksTest extends TestRunner {

    @Test
    public void verifyThatUserCanOpenRozetkaSocialNetworksPages() {
        var socialNetworksSection = homePage.getSocialNetworksSection();

        var softly = new SoftAssertions();

        var facebookPage = socialNetworksSection.openFaceBookPage();
        softly.assertThat(facebookPage.isOpened())
                .as("Rozetka Facebook page should be opened")
                .isTrue();
        facebookPage.back();

        var instagramPage = socialNetworksSection.openInstagramPage();
        softly.assertThat(instagramPage.isOpened())
                .as("Rozetka Instagram page should be opened")
                .isTrue();
        instagramPage.back();

        var telegramPage = socialNetworksSection.openTelegramPage();
        softly.assertThat(telegramPage.isOpened())
                .as("Rozetka Telegram page should be opened")
                .isTrue();
        telegramPage.back();

        var twitterPage = socialNetworksSection.openTwitterPage();
        softly.assertThat(twitterPage.isOpened())
                .as("Rozetka Twitter page should be opened")
                .isTrue();
        twitterPage.back();

        var viberPage = socialNetworksSection.openViberPage();
        softly.assertThat(viberPage.isOpened())
                .as("Rozetka Viber page should be opened")
                .isTrue();
        viberPage.back();

        var youTubePage = socialNetworksSection.openYouTubePage();
        softly.assertThat(youTubePage.isOpened())
                .as("Rozetka YouTube page should be opened")
                .isTrue();
        youTubePage.back();

        softly.assertAll();
    }
}