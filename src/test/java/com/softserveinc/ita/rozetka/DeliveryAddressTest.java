package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.models.DeliveryAddress;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryAddressTest extends LogInViaFacebookTestRunner {

    @Test
    public void verifyThatUserCanAddAddressWithValidData() {

        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var deliveryAddressSection = header
                .openMainSidebar()
                .openProfilePage()
                .openDeliveryAddressSection();

        assertThat(deliveryAddressSection.isOpened())
                .as("Delivery address section should be opened")
                .isTrue();

        var editDeliveryAddressSection = deliveryAddressSection.startEditing();

        if (editDeliveryAddressSection.doesUserHaveAddress()) {
            for (int i = 1; i == editDeliveryAddressSection.getAddressesNumber(); i++) {
                editDeliveryAddressSection.deleteAddress(i);
            }
        }

        var newDeliveryAddress = DeliveryAddress
                .builder()
                .city("Львів")
                .street("Пасічна")
                .building("62")
                .flat("8")
                .build();

        editDeliveryAddressSection
                .addNewAddress()
                .fillInCityField(newDeliveryAddress.getCity())
                .fillInCityFieldByAutocomplete()
                .fillInStreetField(newDeliveryAddress.getStreet())
                .fillInStreetFieldByAutocomplete()
                .fillInBuildingField(newDeliveryAddress.getBuilding())
                .fillInFlatField(newDeliveryAddress.getFlat())
                .save();

        var actualAddress = deliveryAddressSection.getDeliveryAddress();

        var softly = new SoftAssertions();
        softly.assertThat(actualAddress.getCity())
                .as("City should contain data entered during editing")
                .contains(newDeliveryAddress.getCity());
        softly.assertThat(actualAddress.getCity())
                .as("Street should contain data entered during editing")
                .contains(newDeliveryAddress.getCity());
        softly.assertThat(actualAddress.getCity())
                .as("Building should contain data entered during editing")
                .contains(newDeliveryAddress.getCity());
        softly.assertThat(actualAddress.getCity())
                .as("Flat should contain data entered during editing")
                .contains(newDeliveryAddress.getCity());

        softly.assertAll();
    }
}
