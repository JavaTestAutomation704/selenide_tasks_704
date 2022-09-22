package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.City;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PickupPointTest extends BaseTestRunner {

    @Test
    public void verifyDeliveryPointsAddressesAreChangingWhenCityIsChanged() {
        var pickupPointsCityPage = homePage.openPickupPointsCityPage();

        assertThat(pickupPointsCityPage.isOpened())
                .as("City pickup point page should be opened")
                .isTrue();

        pickupPointsCityPage.selectCity(City.DNIPRO);

        assertThat(pickupPointsCityPage.isSelected(City.DNIPRO))
                .as(City.DNIPRO + " should be selected")
                .isTrue();

        var firstCityDeliveryPointsAddresses = pickupPointsCityPage.getDeliveryPointsAddresses();
        var firstCityPickupPointsAddresses = pickupPointsCityPage.getPickupPointsAddresses();

        var softly = new SoftAssertions();

        softly.assertThat(firstCityDeliveryPointsAddresses)
                .as("Delivery and pickup points addresses should be the same")
                .isEqualTo(firstCityPickupPointsAddresses);

        pickupPointsCityPage.selectCity(City.LVIV);

        assertThat(pickupPointsCityPage.isSelected(City.LVIV))
                .as(City.LVIV + " should be selected")
                .isTrue();

        var secondCityDeliveryPointsAddresses = pickupPointsCityPage.getDeliveryPointsAddresses();
        var secondCityPickupPointsAddresses = pickupPointsCityPage.getPickupPointsAddresses();

        softly.assertThat(secondCityDeliveryPointsAddresses)
                .as("Delivery and pickup points addresses should be the same")
                .isEqualTo(secondCityPickupPointsAddresses);

        softly.assertThat(secondCityDeliveryPointsAddresses)
                .as("Addresses list should not be the same in different cities")
                .isNotEqualTo(firstCityDeliveryPointsAddresses);

        softly.assertAll();
    }
}
