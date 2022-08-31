package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;

import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.modals.CatalogModal;
import com.softserveinc.ita.rozetka.modals.LogInModal;

import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class Header {

    @Step("Main sidebar: open main sidebar")
    public MainSidebar openMainSidebar() {
        $x("//rz-mobile-user-menu/button").click();
        return new MainSidebar();
    }

    @Step("Log In modal: start logging")
    public LogInModal startLogging() {
        return new LogInModal().open();
    }

    @Step("Search results page: search for {product}")
    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    @Step("Shopping cart modal: open shopping cart modal")
    public ShoppingCartModal openShoppingCartModal() {
        $x("//button[@rzopencart='']").click();
        $x("//rz-shopping-cart").shouldBe(visible);
        return new ShoppingCartModal();
    }

    public boolean isShoppingCartCounterVisible() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]", 3);
    }

    @Step("Home page: open home page via logo")
    public HomePage openHomePageViaLogo() {
        $x(("//a[@class='header__logo']")).click();
        return new HomePage();
    }

    public boolean isRegisterButtonVisible() {
        return isVisible
                ("//button[@class='auth-modal__register-link button button--link ng-star-inserted']");
    }

    @Step("Catalog modal: open catalog modal")
    public CatalogModal openCatalogModal() {
        $x("//button[@id='fat-menu']").click();
        $x("//a[contains(@href, 'computers-notebooks')]/ancestor::li[contains(@class, 'categories__item')]//div[contains(@class, 'content')]")
                .shouldBe(visible);
        return new CatalogModal();
    }

    public boolean isLanguageSelected(Language language) {
        return isVisible(
                String.format("(//li[contains(@class, 'lang__item')]/span[contains(text(),'%s')])[1]", language));
    }
}