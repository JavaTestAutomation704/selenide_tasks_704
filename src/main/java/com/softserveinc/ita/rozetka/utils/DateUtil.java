package com.softserveinc.ita.rozetka.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        try {
            return getDateFromString(elementXpath).format(ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            return "";
        }
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
}