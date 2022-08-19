package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import static utils.WebElementUtil.isVisible;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage open(String name) {
        return new CategoryPage();
    }

    public boolean isSmallCartSectionVisible(){
        return isVisible("//rz-app-small-cart");
    }

    public boolean isMainCategoriesSectionVisible(){
        return isVisible("//rz-app-fat-menu-tablet");
    }
}