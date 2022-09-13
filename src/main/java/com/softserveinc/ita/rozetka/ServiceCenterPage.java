package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.service_center.ProducerPage;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class ServiceCenterPage {

    @Step("Service center page: select first letter {firstLetter}")
    public ServiceCenterPage selectFirstLetter(char firstLetter) {
        $x(format("//a[@class='chips__button' and contains(text(),'%s')]", valueOf(firstLetter).toUpperCase())).click();
        return this;
    }

    public List<String> getProducersList() {
        try {
            return $$x("//a[contains(@class, 'service-list')]").texts();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public boolean isServiceCenterListVisible() {
        return isVisible("//rz-brands");
    }

    @Step("Service center page: search producer by name {producerName}")
    public ProducerPage searchProducer(String producerName) {
        $x("//input[contains(@class,'autocomplete__input')]").setValue(producerName);
        $x("(//ul[contains(@class,'autocomplete')]/li)[1]").click();
        return new ProducerPage();
    }
}
