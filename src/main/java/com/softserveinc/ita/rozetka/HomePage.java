package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;

import static utils.WebElementUtil.isVisible;

public class HomePage extends BasePage {
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public boolean isSmallCartSectionVisible(){
        return isVisible("//rz-app-small-cart");
    }

    public boolean isMainCategoriesSectionVisible(){
        return isVisible("//rz-app-fat-menu-tablet");
    }
}