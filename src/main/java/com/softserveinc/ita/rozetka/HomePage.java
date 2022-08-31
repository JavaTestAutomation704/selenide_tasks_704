package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.data.Category;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class HomePage extends BasePage {

    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public boolean isSmallCartSectionVisible() {
        return isVisible("//rz-app-small-cart");
    }

    public boolean isMainCategoriesSectionVisible() {
        return isVisible("//rz-app-fat-menu-tablet");
    }

    @Step("Category page: open category page {category}")
    public CategoryPage openCategoryPage(Category category) {
        $x(String.format("//div[@class = 'fat-wrap']//a[contains(@href, '%s')]", category.getCategoryXpath())).click();
        return new CategoryPage();
    }
}