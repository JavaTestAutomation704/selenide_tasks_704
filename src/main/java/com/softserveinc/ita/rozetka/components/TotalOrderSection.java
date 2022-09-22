package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.utils.WebElementUtil;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

public class TotalOrderSection {

    public boolean isVisible() {
        return WebElementUtil.isVisible("//div[@class='checkout-total']");
    }

    public long getTotalSum() {
        return Long.parseLong(getText("//dl[contains(@class, 'js-total')]/dd")
                .replaceAll("[^0-9]+", ""));
    }
}
