package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import com.softserveinc.ita.rozetka.modals.CatalogModal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

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
        waitVisibility("//div[contains(@class, 'modal__holder')]");
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
        $x("//button[@id='fat-menu']").click();
        waitVisibility("//a[contains(@href, 'computers-notebooks')]/ancestor::li[contains(@class, 'categories__item')]//div[contains(@class, 'content')]");
        return new CatalogModal();
    }
}