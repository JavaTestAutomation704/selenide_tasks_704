package com.softserveinc.ita.rozetka;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CreditPage {

    public boolean isOpened() {
        return isVisible("//div[@class='rz-credit-block']");
    }
}