package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ComparisonItem;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ComparisonPage extends BasePage {

    public ComparisonItem getComparisonItem(int number) {
        return new ComparisonItem(number);
    }

    public int getComparisonItemQuantity() {
        return getCollectionSize("//rz-compare-tile", 2);
    }

    public boolean isNothingToCompareMessageVisible(){
        return isVisible("//rz-no-products-to-compare/div", 3);
    }
}