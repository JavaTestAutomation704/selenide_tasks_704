package com.softserveinc.ita.rozetka.components;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ReviewItem {
    private final String reviewItemXpath;

    public ReviewItem(int number) {
        reviewItemXpath = String.format("((//li[contains(@class, 'product-comments__list')])[%s]//div[@class='comment__inner'])[1]", number);
        $x(reviewItemXpath).scrollIntoView(false);
    }

    public int getRating() {
        return getCollectionSize(reviewItemXpath + "//rz-gradient-star//*[local-name()='path' and @fill='#ffa900']");
    }

    public boolean hasPhoto() {
        return isVisible(reviewItemXpath + "//img", 2);
    }
}
