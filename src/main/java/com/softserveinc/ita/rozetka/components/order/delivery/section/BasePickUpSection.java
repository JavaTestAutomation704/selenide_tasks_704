package com.softserveinc.ita.rozetka.components.order.delivery.section;

import com.softserveinc.ita.rozetka.modals.PickUpPointModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public abstract class BasePickUpSection extends BaseDeliverySection {

    public BasePickUpSection(int orderNumber) {
        super(orderNumber);
    }

    public String getPickUpPointName() {
        return $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).text();
    }

    @Step("Base pickup section: select Meest pickup point department number {departmentNumber}")
    public BasePickUpSection selectPickUpPointDepartment(int departmentNumber) {
        $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).click();
        $x(format("//ul[contains(@class,'list-inner')]//div[contains(text(),'%d')]", departmentNumber)).click();
        return this;
    }

    @Step("Base pickup section: select pickup point department on {departmentAddress}")
    public BasePickUpSection selectPickUpPointDepartment(String departmentAddress) {
        $x(format("(//button[contains(@class,'dropdown-button')])[%d]", orderNumber)).click();
        $x(format("//ul[contains(@class,'list-inner')]//div[contains(text(),'%s')]", departmentAddress)).click();
        return this;
    }

    @Step("Base pickup section: open pickup point modal")
    public PickUpPointModal openPickUpPointModal() {
        $x(format("(//button[contains(@class,'delivery-pickups__map')])[%d]", orderNumber)).click();
        return new PickUpPointModal();
    }
}