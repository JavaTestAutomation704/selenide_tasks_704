package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.components.profile.EditPersonalDataSection;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class Calendar {

    @Getter
    private String selectedDate;

    public boolean isOpened() {
        return isVisible("//dp-day-calendar");
    }

    @Step("Calendar: select current date")
    public EditPersonalDataSection selectCurrentDate() {
        $x("//button[contains(@class, 'nav-header')]").click();
        $x("//button[contains(@class, 'current-location')]").click();
        $x("//button[contains(@class, 'current-month')]").click();
        $x("//button[contains(@class, 'current-day')]").click();
        return new EditPersonalDataSection();
    }

    @Step("Calendar: select random date")
    public EditPersonalDataSection selectRandomDate() {
        var random = new Random();
        int stepsToPreviousMonths = random.nextInt(20);
        int stepsToNextMonths = random.nextInt(stepsToPreviousMonths);
        for (int step = 0; step <= stepsToPreviousMonths; step++) {
            $x("//button[contains(@class, 'nav-left')]").click();
        }

        for (int step = 0; step <= stepsToNextMonths; step++) {
            $x("//button[contains(@class, 'nav-right')]").click();
        }

        int randomDay = random.nextInt(getCollectionSize("//button[contains(@class, 'current-month')]"));

        var currentMonthDayXpath = "(//button[contains(@class, 'current-month')])[%d]";
        selectedDate = getAttribute(format(currentMonthDayXpath, randomDay), "data-date");
        $x(format(currentMonthDayXpath, randomDay)).click();

        return new EditPersonalDataSection();
    }
}