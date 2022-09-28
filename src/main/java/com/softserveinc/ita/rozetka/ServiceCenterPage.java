package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.service_center.ProducerPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class ServiceCenterPage {

    @Step("Service center page: select first letter {firstLetter}")
    public ServiceCenterPage selectFirstLetter(char firstLetter) {
        var firstWordXpath = "(//a[contains(@class,'service-list')])[1]";
        var firstWord = getText(firstWordXpath);
        $x(format("//a[@class='chips__button' and contains(text(),'%s')]",
                valueOf(firstLetter).toUpperCase())).click();
        if (!firstWord.isEmpty()) {
            waitForTextChange(firstWordXpath, firstWord);
        } else {
            waitTillVisible(firstWordXpath);
        }
        return this;
    }

    public List<String> getProducersList() {
        return getElementsText("//li[contains(@class, 'service-list')]");
    }

    public boolean isServiceCenterListVisible() {
        return isVisible("//rz-brands");
    }

    @Step("Service center page: search producer by name {producerName}")
    public ProducerPage searchProducer(String producerName) {
        $x("//input[contains(@class,'autocomplete__input')]").setValue(producerName);
        $x(format("//div/ul//*[contains(text(),'%s')]", producerName)).click();
        return new ProducerPage();
    }
}