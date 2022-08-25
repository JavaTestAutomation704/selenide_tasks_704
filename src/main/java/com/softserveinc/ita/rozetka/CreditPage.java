package com.softserveinc.ita.rozetka;

import static utils.WebElementUtil.isVisible;

public class CreditPage {

    public boolean isOpen() {
        boolean isTitleVisible = isVisible("//h1[contains(text(), 'Кредитні пропозиції')]");
        boolean isCreditInfoBlocksVisible = isVisible("//div[@class='rz-credit-block']");
        return isTitleVisible && isCreditInfoBlocksVisible;
    }
}
