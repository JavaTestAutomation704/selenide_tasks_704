package com.softserveinc.ita.rozetka;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class RozetkaGooglePlayPage extends DownloadApplicationsBasePage {

    public RozetkaGooglePlayPage open() {
        open("play.google");
        return this;
    }

    @Override
    protected boolean isOpened() {
        return isVisible("//title[contains(text(), 'Google Play')]/ancestor::html//a[contains(@href, 'id=Rozetka')]");
    }
}