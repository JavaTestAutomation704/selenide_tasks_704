package com.softserveinc.ita.rozetka.service_center;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ProducerPage {

    @Step("Producer page: select category {categoryName}")
    public CategoryPage selectCategory(String categoryName) {
        $x(format("//a[contains(text(), '%s')]", categoryName)).click();
        return new CategoryPage();
    }
}