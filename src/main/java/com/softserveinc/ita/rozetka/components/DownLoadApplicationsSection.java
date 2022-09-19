package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.download_application.pages.RozetkaAppStorePage;
import com.softserveinc.ita.rozetka.download_application.pages.RozetkaGooglePlayPage;

public class DownLoadApplicationsSection {

    public RozetkaGooglePlayPage openRozetkaGooglePlayPage() {
        return new RozetkaGooglePlayPage().open();
    }

    public RozetkaAppStorePage openRozetkaAppStorePage() {
        return new RozetkaAppStorePage().open();
    }
}
