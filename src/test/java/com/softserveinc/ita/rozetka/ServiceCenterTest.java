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
        var serviceCenterPage = homePage.openServiceCenterPage();

        assertThat(serviceCenterPage.isServiceCenterListVisible())
                .as("Service center list should be visible")
                .isTrue();
        var softly = new SoftAssertions();

        asList('A', 'B', 'C', 'D', 'E', '1', '2', '3').forEach(character -> {
            var producersList = serviceCenterPage
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
        var resultPage = serviceCenterPage
                .searchProducer(producerName)
                .selectCategory(categoryName)
                .selectCity("Київ");

        softly.assertThat(resultPage.getTitle())
                .as("Title should contain correct data")
                .contains(producerName, categoryName);
        softly.assertThat(resultPage.getServiceInfoSize())
                .as("List should contain information about at least one service center")
                .isGreaterThan(0);
        softly.assertAll();
    }
}
