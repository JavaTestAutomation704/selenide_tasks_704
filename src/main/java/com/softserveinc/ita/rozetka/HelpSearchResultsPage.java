package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.HelpCenterHeader;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class HelpSearchResultsPage {

    private final String linkTitleXpath = "(//h3[@class='h5 ih'])";

    @Getter
    private final HelpCenterHeader helpCenterHeader = new HelpCenterHeader();

    public boolean isOpened() {
        return isVisible("//div[@class = 'search-list']", 15);
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
}