package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.closeCurrentTabAndSwitchTo;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadApplicationTest extends BaseTestRunner {

    @Test
    public void verifyUserCanOpenRozetkaDownloadApplicationPage() {
        var mainSideBar = homePage
                .getHeader()
                .openMainSidebar();

        assertThat(mainSideBar.isOpened())
                .as("Main side bar should be opened")
                .isTrue();

        var downloadApplicationSection = mainSideBar.getDownloadApplicationSection();
        var rozetkaGooglePlayPage = downloadApplicationSection.openRozetkaGooglePlayPage();

        var softly = new SoftAssertions();

        softly.assertThat(rozetkaGooglePlayPage.isOpened())
                .as("Rozetka google play page should be opened")
                .isTrue();

        closeCurrentTabAndSwitchTo(0);

        var rozetkaAppStorePage = downloadApplicationSection.openRozetkaAppStorePage();

        softly.assertThat(rozetkaAppStorePage.isOpened())
                .as("Rozetka app store page should be opened")
                .isTrue();

        softly.assertAll();
    }
}