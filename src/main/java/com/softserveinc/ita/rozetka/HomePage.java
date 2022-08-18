package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;

public class HomePage extends BasePage {
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }
}