package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelpCenterSearchTest extends TestRunner {

    @Test
    public void verifyHelpCenterSearchFunctionality() {
        var contactsPage = homePage
                .getCompanyInformationSection()
                .openContactsPage();

        assertThat(contactsPage.isOpened())
                .as("Contacts page should be opened")
                .isTrue();

        var helpCenterPage = contactsPage.openHelpCenterPage();

        assertThat(helpCenterPage.isOpened())
                .as("Help center page should be opened")
                .isTrue();

        var keyword = "доставка";

        var helpSearchResultsPage = helpCenterPage.search(keyword);

        assertThat(helpSearchResultsPage.isOpened())
                .as("Help search results page should be opened")
                .isTrue();

        var softly = new SoftAssertions();
        softly.assertThat(helpSearchResultsPage.doesTitleContain(keyword))
                .as("Title should contains searched keyword")
                .isTrue();

        assertThat(helpSearchResultsPage.getResultsAmount())
                .as("Links quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var firstSearchResultsLinkTitle = helpSearchResultsPage.getTitle(1);

        assertThat(firstSearchResultsLinkTitle)
                .as("First link should contains searched keyword")
                .contains(keyword);

        var helpCenterSearchMenu = helpSearchResultsPage
                .getHelpCenterHeader()
                .openHelpCenterSearchMenu(keyword);

        assertThat(helpCenterSearchMenu.isOpened())
                .as("Help center search menu should be opened")
                .isTrue();

        softly.assertThat(helpCenterSearchMenu.getTitle(1))
                .as("Titles should be the same")
                .isEqualTo(firstSearchResultsLinkTitle);

        softly.assertAll();
    }
}
