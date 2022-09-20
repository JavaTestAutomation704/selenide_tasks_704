package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.data.Month;

import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

public class ReviewItem {
    private final String reviewItemXpath;

    public ReviewItem(int number) {
        reviewItemXpath = format("((//li[contains(@class, 'product-comments__list')])[%s]//div[@class='comment__inner'])[1]", number);
        $x(reviewItemXpath).scrollIntoView(false);
    }

    public int getRating() {
        return getCollectionSize(reviewItemXpath + "//rz-gradient-star//*[local-name()='path' and @fill='#ffa900']");
    }

    public boolean hasPhoto() {
        return isVisible(reviewItemXpath + "//img", 2);
    }

    public Date getDate() {
        return parseDate($x(reviewItemXpath + "//time").text());
    }

    private Date parseDate(String inputDate) {
        var calendar = Calendar.getInstance();

        if (inputDate.equals("вчора")) {
            calendar.add(Calendar.DATE, -1);
        } else if (inputDate.contains(" ")) {
            var dateParts = inputDate.split(" ");

            calendar.set(Calendar.DATE, Integer.parseInt(dateParts[0]));
            calendar.set(Calendar.MONTH, Month.getNumberByValue(dateParts[1]));
            calendar.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
        }

        // The time must be reset because the date on the site did not contain a time
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
