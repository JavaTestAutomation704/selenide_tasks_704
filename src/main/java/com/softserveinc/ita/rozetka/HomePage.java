package com.softserveinc.ita.rozetka;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.components.SmallCart;
import com.softserveinc.ita.rozetka.data.Category;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class HomePage extends BasePage {

    @Step("Home page: open home page")
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

    @Step("Home page: open category page {category}")
    public CategoryPage openCategoryPage(Category category) {
        $x(String.format("//div[@class = 'fat-wrap']//a[contains(@href, '%s')]", category.getCategoryXpath())).click();
        return new CategoryPage();
    }

    public SmallCart getSmallCart() {
        return new SmallCart();
    }

    @Step("Home page: open pickup points city page")
    public PickupPointsCityPage openPickupPointsCityPage() {
        $x("//a[contains(@href, 'retail')]").click();
        return new PickupPointsCityPage();
    }
}