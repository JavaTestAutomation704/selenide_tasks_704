package com.softserveinc.ita.rozetka;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class RozetkaAppStorePage extends DownloadApplicationsBasePage {

    public RozetkaAppStorePage open() {
        open("apple");
        return this;
    }

    @Override
    protected boolean isOpened() {
        return isVisible("(//a[contains(@href, 'apple.com') and contains(@href, 'rozetka')])[1]");
    }
}
