package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.data.Category;

import static com.codeborne.selenide.Selenide.$x;

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
    
    public CategoryPage openCategoryPage(Category category) {
        $x(String.format("//div[@class = 'fat-wrap']//a[contains(@href, '%s')]", category.getCategoryXpath())).click();
        return new CategoryPage();
    }
}