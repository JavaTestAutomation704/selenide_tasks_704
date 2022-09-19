package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.social_networks.pages.*;
import io.qameta.allure.Step;

public class SocialNetworksSection {

    @Step("Social networks section: open Facebook page")
    public FacebookPage openFaceBookPage() {
        return new FacebookPage().open();
    }

    @Step("Social networks section: open Instagram page")
    public InstagramPage openInstagramPage() {
        return new InstagramPage().open();
    }

    @Step("Social networks section: open Telegram page")
    public TelegramPage openTelegramPage() {
        return new TelegramPage().open();
    }

    @Step("Social networks section: open Twitter page")
    public TwitterPage openTwitterPage() {
        return new TwitterPage().open();
    }

    @Step("Social networks section: open Viber page")
    public ViberPage openViberPage() {
        return new ViberPage().open();
    }

    @Step("Social networks section: open YouTube page")
    public YouTubePage openYouTubePage() {
        return new YouTubePage().open();
    }
}