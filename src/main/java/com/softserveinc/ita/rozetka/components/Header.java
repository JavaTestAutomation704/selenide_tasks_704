package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.modals.CatalogModal;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class Header {

    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }

    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartModal() {
        $x("//button[@rzopencart='']").click();
        return new ShoppingCartModal();
    }
    
    public boolean isShoppingCartCounterVisible() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]");
    }
    
    public HomePage openHomePageViaLogo() {
        $x(("//a[@class='header__logo']")).click();
        return new HomePage();
    }
    public boolean isRegisterButtonVisible() {
        return isVisible
                ("//button[@class='auth-modal__register-link button button--link ng-star-inserted']");
    }
    public CatalogModal openCatalogModal(){
        $("button button--medium button--with-icon menu__toggle ng-star-inserted").click();
        return new CatalogModal();
    }
}