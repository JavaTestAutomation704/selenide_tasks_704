package com.softserveinc.ita.rozetka.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.time.format.DateTimeFormatter.ofPattern;

@UtilityClass
public class DateUtil {

    public static LocalDate getDateFromString(String elementXpath) {
        Locale.setDefault(Locale.forLanguageTag("uk-UA"));
        return LocalDate.parse(getText(elementXpath), ofPattern("d MMMM yyyy"));
    }

    public static String getFormattedDateFromString(String elementXpath, String dateFormat) {
        return getDateFromString(elementXpath).format(ofPattern(dateFormat));
    }

    public static String getRandomPastDate() {
        int randomNumber = new Random().nextInt(30);
        // TODO: This guarantees that the date is always different
        var date = LocalDate
                .now()
                .minusYears(randomNumber)
                .plusDays(randomNumber);

        return date.format(ofPattern("dd-MM-yyyy"));
    }

    public static LocalDate getDateWithDays(String elementXpath) {
        var inputDate = getText(elementXpath);

        if (inputDate.contains(" ")) {
            return LocalDate.parse(inputDate, ofPattern("d MMMM yyyy"));
        }

        if (inputDate.equals("вчора")) {
            return LocalDate.now().minusDays(1);
        }
        return LocalDate.now();
    }
}