package com.softserveinc.ita.rozetka.components.goodsExchangePage;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitForTextChange;
import static java.lang.String.format;

public abstract class BaseStatusForm {

    protected void selectRadioButton(int number) {
        $x(format("(//input[@type='radio'])[%d]", number)).click();
    }

    protected void openNext() {
        var titleXpath = "//div[@class='title-question']";
        var title = $x(titleXpath).text();
        $x("//button[contains(@class,'button-next')]").click();
        waitForTextChange(titleXpath, title);
    }
}
