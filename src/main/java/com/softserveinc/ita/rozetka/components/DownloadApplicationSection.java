package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.download_application.pages.RozetkaAppStorePage;
import com.softserveinc.ita.rozetka.download_application.pages.RozetkaGooglePlayPage;
import io.qameta.allure.Step;

public class DownloadApplicationSection {

    @Step("Download application section: open rozetka google play page")
    public RozetkaGooglePlayPage openRozetkaGooglePlayPage() {
        return new RozetkaGooglePlayPage().open();
    }

    @Step("Download application section: open rozetka app store page")
    public RozetkaAppStorePage openRozetkaAppStorePage() {
        return new RozetkaAppStorePage().open();
    }
}
