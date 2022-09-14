package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class HelpCenterSearchMenu {

    private final String helpCenterSearchMenuXpath = "//div[@id = 'tt-search-list' and contains(@class, 'show')]";

    public boolean isOpened() {
        return isVisible(helpCenterSearchMenuXpath);
    }

    public String getTitle(int linkNumber) {
        return $x(format("(%s//a/div)[%d]", helpCenterSearchMenuXpath, linkNumber)).text();
    }
}
