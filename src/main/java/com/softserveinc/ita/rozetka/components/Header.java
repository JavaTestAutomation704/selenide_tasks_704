package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.WebDriverRunner;
import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.modals.CatalogModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class Header {
    private final String authorizationXpath = "//button[contains(@class,'side-menu__auth-button')]";

    public MobileMenu openMobileMenu() {
        $x("//rz-mobile-user-menu/button").click();
        return new MobileMenu();
    }


    public String getUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public Header logInViaUserIcon() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }

    public boolean isShoppingCartEmpty() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]");
    }

    public boolean isShoppingCartModalVisible() {
        return isVisible("//div[contains(@class, 'modal__holder')]");
    }

    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    public boolean isRegisterButtonVisible() {
        return isVisible("//button[@class='auth-modal__register-link button button--link ng-star-inserted']");
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }

    public String getCurrentCity() {
        return "";
    }

    public Header changeCity(String city) {
        return this;
    }

    public HomePage openHomePageViaLogo() {
        return new HomePage();
    }

    public CatalogModal openCatalog() {
        return new CatalogModal();
    }
}