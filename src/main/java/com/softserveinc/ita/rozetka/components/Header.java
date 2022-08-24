package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.modals.RegistrationModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.openqa.selenium.By;

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

    public LogInModal startLoggingIn() {
        return new LogInModal().open();
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
    public RegistrationModal startRegistrationModal(){
        return new RegistrationModal().open();
    }

    public CatalogModal openCatalogModal(){
        $("button button--medium button--with-icon menu__toggle ng-star-inserted").click();
        return new CatalogModal();
    }
}