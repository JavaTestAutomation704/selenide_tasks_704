package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitForTextChange;

public abstract class BaseStatusForm {

    protected String statusXpathTemplate = "(//input[@type='radio'])[%d]";

    protected void openNext() {
        var titleXpath = "//div[@class='title-question']";
        var title = $x(titleXpath).text();
        $x("//button[contains(@class,'button-next')]").click();
        waitForTextChange(titleXpath, title);
    }
}
