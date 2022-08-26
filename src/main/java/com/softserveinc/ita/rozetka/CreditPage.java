package com.softserveinc.ita.rozetka;

import static utils.WebElementUtil.isVisible;

public class CreditPage {

    public boolean isOpen() {
        boolean isTitleVisible = isVisible("//h1[@class='rz-credit-title']");
        boolean isCreditInfoBlocksVisible = isVisible("//div[@class='rz-credit-block']");
        return isTitleVisible && isCreditInfoBlocksVisible;
    }
}
