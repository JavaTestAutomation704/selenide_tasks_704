package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class HelpCenterPage {

    public boolean isOpened() {
        return isVisible("//div[@class = 'search-section py-4']");
    }

    @Step("Help center page: search {keyword}")
    public HelpSearchResultsPage search(String keyword) {
        $x("//input[@id = 'ss-block']").val(keyword).pressEnter();
        return new HelpSearchResultsPage();
    }
}
