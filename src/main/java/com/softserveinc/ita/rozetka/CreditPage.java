package com.softserveinc.ita.rozetka;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CreditPage {

    public boolean isOpen() {
        var isTitleVisible = isVisible("//h1[@class='rz-credit-title']");
        var isCreditInfoBlocksVisible = isVisible("//div[@class='rz-credit-block']");
        return isTitleVisible && isCreditInfoBlocksVisible;
    }
}
