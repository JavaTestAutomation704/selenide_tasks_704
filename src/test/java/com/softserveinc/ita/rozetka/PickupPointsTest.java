package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.City;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PickupPointsTest extends TestRunner {

    @Test
    public void verifyAddressesCorrespondenceWhenCityIsChanged() {
        var cityRetailPage = homePage.openCityRetailPage();

        assertThat(cityRetailPage.isOpened())
                .as("City retail page should be opened")
                .isTrue();

        cityRetailPage.selectCity(City.DNIPRO);

        assertThat(cityRetailPage.isSelected(City.DNIPRO))
                .as("Dnipro city should be selected")
                .isTrue();

        var deliveryPointsAddressesDnipro = cityRetailPage.getDeliveryPointsAddresses();
        var pickupPointsAddressesDnipro = cityRetailPage.getPickupPointsAddresses();

        var softly = new SoftAssertions();

        softly.assertThat(cityRetailPage.getDeliveryPointsAmount())
                .as("Delivery points amount should be equal to pickup point amount in Dnipro city")
                .isEqualTo(cityRetailPage.getPickupPointsAmount());

        softly.assertThat(deliveryPointsAddressesDnipro)
                .as("Delivery and pickup points addresses in Dnipro city should be the same")
                .isEqualTo(pickupPointsAddressesDnipro);

        cityRetailPage.selectCity(City.LVIV);

        assertThat(cityRetailPage.isSelected(City.LVIV))
                .as("Lviv city should be selected")
                .isTrue();

        var deliveryPointsAddressesLviv = cityRetailPage.getDeliveryPointsAddresses();
        var pickupPointsAddressesLviv = cityRetailPage.getPickupPointsAddresses();

        softly.assertThat(cityRetailPage.getDeliveryPointsAmount())
                .as("Delivery points amount should be equal to pickup point amount in Lviv city")
                .isEqualTo(cityRetailPage.getPickupPointsAmount());

        softly.assertThat(deliveryPointsAddressesLviv)
                .as("Delivery and pickup points addresses in Lviv city should be the same")
                .isEqualTo(pickupPointsAddressesLviv);

        softly.assertAll();
    }
}
