package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.HelpCenterHeader;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class HelpSearchResultsPage {

    private final String linkTitleXpath = "(//h3[@class='h5 ih'])";

    public boolean isOpened() {
        var searchListXpath = "//div[@class = 'search-list']";
        waitTillVisible(searchListXpath, 10);
        return isVisible(searchListXpath);
    }

    public boolean doesTitleContain(String keyword) {
        return isVisible(format("//h2[@class = 'my-4 search_title' and contains(text(), '%s')]", keyword));
    }

    public String getTitle(int linkNumber) {
        return $x(format(linkTitleXpath + "[%d]", linkNumber)).text();
    }

    public long getResultsAmount() {
        return getCollectionSize(linkTitleXpath);
    }

    public HelpCenterHeader getHelpCenterHeader() {
        return new HelpCenterHeader();
    }
}
