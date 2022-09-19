package com.softserveinc.ita.rozetka.service_center;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage {

    @Step("Category page: select city {cityName}")
    public ResultPage selectCity(String cityName) {
        $x(format("//a[contains(text(), '%s')]", cityName)).click();
        return new ResultPage();
    }
}
