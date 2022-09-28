package com.softserveinc.ita.rozetka.components.profile;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

@RequiredArgsConstructor
public class EditDeliveryAddressSection {

    private final String deliveryAddressSectionXpath;
    private final String deleteButtonsXpath = "//button[contains(@class, 'button_type_icon button_size_medium button_color_white')]";

    @Step("Edit delivery address section: save")
    public DeliveryAddressSection save() {
        String saveButtonXpath = "//button[@type='submit']";
        $x(saveButtonXpath).click();
        return new DeliveryAddressSection(deliveryAddressSectionXpath);
    }

    @Step("Edit delivery address section: add new address")
    public EditDeliveryAddressSection addNewAddress() {
        $x("(//fieldset[@class='form__row'])[2]//button").click();
        return this;
    }

    @Step("Edit delivery address section: fill in city field {city}")
    public EditDeliveryAddressSection fillInCityField(String city) {
        String inputCityXpath = "//input)[1]";
        $x(String.format("(%s%s", deliveryAddressSectionXpath, inputCityXpath)).val(city);
        return this;
    }

    @Step("Edit delivery address section: complete filling in city field by using autocomplete")
    public EditDeliveryAddressSection fillInCityFieldByAutocomplete() {
        $x("(//li[@class='autocomplete__item ng-star-inserted'])[1]").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Edit delivery address section: fill in street field {street}")
    public EditDeliveryAddressSection fillInStreetField(String street) {
        String inputStreetXpath = "//input)[2]";
        $x(String.format("(%s%s", deliveryAddressSectionXpath, inputStreetXpath)).val(street);
        return this;
    }

    @Step("Edit delivery address section: complete filling in street field by using autocomplete")
    public EditDeliveryAddressSection fillInStreetFieldByAutocomplete() {
        $x("(//li[@class='autocomplete__item ng-star-inserted'])[1]").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Edit delivery address section: fill in building field {building}")
    public EditDeliveryAddressSection fillInBuildingField(String building) {
        String inputBuildingXpath = "//input)[3]";
        $x(String.format("(%s%s", deliveryAddressSectionXpath, inputBuildingXpath)).val(building);
        return this;
    }

    @Step("Edit delivery address section: fill in flat field {flat}")
    public EditDeliveryAddressSection fillInFlatField(String flat) {
        String inputFlatXpath = "//input)[4]";
        $x(String.format("(%s%s", deliveryAddressSectionXpath, inputFlatXpath)).val(flat);
        return this;
    }

    public boolean doesUserHaveAddress() {
        return isVisible(deleteButtonsXpath);
    }

    public int getAddressesNumber() {
        return getCollectionSize(deleteButtonsXpath);
    }

    @Step("Edit delivery address section: delete address")
    public EditDeliveryAddressSection deleteAddress(int number) {
        $x(String.format("(%s)[%s]", deleteButtonsXpath, number)).click();
        return this;
    }
}
