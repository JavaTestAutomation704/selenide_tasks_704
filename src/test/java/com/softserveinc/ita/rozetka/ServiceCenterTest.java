package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static java.lang.String.valueOf;
import static org.testng.Assert.assertTrue;

public class ServiceCenterTest extends TestRunner {

    @Test
    public void verifyServiceCenterFunctionalityWorks() {
        var serviceCenter = homePage.openServiceCenterPage();

        assertTrue(serviceCenter.isServiceCenterListVisible());
        var softly = new SoftAssertions();

        for (char c = 'A'; c <= 'J'; c++) {
            var producersList = serviceCenter
                    .selectFirstLetter(c)
                    .getProducersList();
            var firstLetter = valueOf(c).toUpperCase();

            var areListNamesCorrect = producersList.size() != 0 && producersList
                    .stream()
                    .allMatch(a -> a.substring(0, 1).toUpperCase().equals(firstLetter));

            softly.assertThat(areListNamesCorrect)
                    .as("All names should start with the same letter %s - %s ", firstLetter, producersList)
                    .isTrue();
        }

        var producerName = "Apple";
        var categoryName = "Смарт-годинники";
        var resultPage = serviceCenter
                .searchProducer(producerName)
                .selectCategory(categoryName)
                .selectCity("Київ");

        softly.assertThat(resultPage.getTitle())
                .as("Title should contain correct data")
                .contains(producerName)
                .contains(categoryName);
        softly.assertThat(resultPage.getServiceInfoSize())
                .as("List should contain information about at least one service center")
                .isGreaterThan(0);
        softly.assertAll();
    }
}
