package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.Month;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ProductQuestionItem {
    private final String productQuestionItemXpath;

    public ProductQuestionItem(int number) {
        productQuestionItemXpath = String.format("((//li[contains(@class, 'product-questions__list')])[%s]//div[@class='comment__inner'])[1]", number);
        $x(productQuestionItemXpath).scrollIntoView(false);
    }

    public boolean hasPhoto() {
        return isVisible(productQuestionItemXpath + "//ul[@class='product-comments__photos-list ng-star-inserted']", 2);
    }

    public Date getDate() {
        return parseDate($x(productQuestionItemXpath + "//time").text());
    }

    private Date parseDate(String inputDate) {
        var dateParts = inputDate.split(" ");
        var calendar = new GregorianCalendar(Integer.parseInt(dateParts[2]), Month.getNumberByValue(dateParts[1]),
                Integer.parseInt(dateParts[0]));

        return calendar.getTime();
    }
}