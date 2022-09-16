package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static java.lang.String.valueOf;

public class ServiceCenterTest extends TestRunner {

    @Test
    public void verifyServiceCenterFunctionalityWorks() {
        var serviceCenter = homePage.openServiceCenterPage();

        assertThat(serviceCenter.isServiceCenterListVisible())
                .as("Service center list should be visible")
                .isTrue();
        var softly = new SoftAssertions();

        asList('A', 'B', 'C', 'D', 'E', '1', '2', '3').forEach(character -> {
            var producersList = serviceCenter
                    .selectFirstLetter(character)
                    .getProducersList();

            var areListNamesCorrect = producersList
                    .stream()
                    .allMatch(producerName -> producerName
                            .toUpperCase()
                            .startsWith((valueOf(character))));

            softly.assertThat(areListNamesCorrect)
                    .as("All names should start with the same letter")
                    .isTrue();
        });

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
