package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.City;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PickupPointTest extends TestRunner {

    @Test
    public void verifyAddressesCorrespondenceWhenCityIsChanged() {
        var cityRetailPage = homePage.openCityRetailPage();

        assertThat(cityRetailPage.isOpened())
                .as("City retail page should be opened")
                .isTrue();

        cityRetailPage.selectCity(City.DNIPRO);

        assertThat(cityRetailPage.isSelected(City.DNIPRO))
                .as(City.DNIPRO.getCity() + " city should be selected")
                .isTrue();

        var deliveryPointsAddressesDnipro = cityRetailPage.getDeliveryPointsAddresses();
        var pickupPointsAddressesDnipro = cityRetailPage.getPickupPointsAddresses();

        var softly = new SoftAssertions();

        softly.assertThat(deliveryPointsAddressesDnipro)
                .as("Delivery and pickup points addresses should be the same in city " + City.DNIPRO.getCity())
                .isEqualTo(pickupPointsAddressesDnipro);

        cityRetailPage.selectCity(City.LVIV);

        assertThat(cityRetailPage.isSelected(City.LVIV))
                .as(City.LVIV.getCity() + " city should be selected")
                .isTrue();

        var deliveryPointsAddressesLviv = cityRetailPage.getDeliveryPointsAddresses();
        var pickupPointsAddressesLviv = cityRetailPage.getPickupPointsAddresses();

        softly.assertThat(deliveryPointsAddressesLviv)
                .as("Delivery and pickup points addresses should be the same in city " + City.LVIV.getCity())
                .isEqualTo(pickupPointsAddressesLviv);

        softly.assertThat(deliveryPointsAddressesLviv)
                .as("Addresses list should not be the same in different cities")
                .isNotEqualTo(deliveryPointsAddressesDnipro);

        softly.assertAll();
    }
}
