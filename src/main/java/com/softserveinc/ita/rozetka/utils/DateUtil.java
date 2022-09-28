package com.softserveinc.ita.rozetka.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Random;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.time.format.DateTimeFormatter.ofPattern;

@UtilityClass
public class DateUtil {

    public static LocalDate getDateFromString(String elementXpath, String dateFormat, String langTag) {
        Locale.setDefault(Locale.forLanguageTag(langTag));
        return LocalDate.parse(getText(elementXpath), ofPattern(dateFormat));
    }

    public static String getFormattedDateFromString(String elementXpath, String dateFormat, String strDateFormat, String langTag) {
        Locale.setDefault(Locale.forLanguageTag(langTag));
        try {
            return getDateFromString(elementXpath, dateFormat, langTag).format(ofPattern(strDateFormat));
        } catch (DateTimeParseException exception) {
            return "";
        }
    }

    public static String getFormattedCurrentDate(String strDateFormat) {
        return LocalDate
                .now()
                .format(DateTimeFormatter.ofPattern(strDateFormat));
    }

    public static String getRandomPastDate(String strDateFormat) {
        int randomNumber = new Random().nextInt(30);
        // TODO: This guarantees that the date is always different
        var date = LocalDate
                .now()
                .minusYears(randomNumber)
                .plusDays(randomNumber);

        return date.format(ofPattern(strDateFormat));
    }
}