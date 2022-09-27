package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.HomePage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.WishlistPage;
import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.modals.CatalogModal;
import com.softserveinc.ita.rozetka.modals.ComparisonListModal;
import com.softserveinc.ita.rozetka.modals.LogInModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import com.softserveinc.ita.rozetka.profile.MyOrdersPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class Header {

    private final String searchFieldCss = "search";

    @Step("Header: open main sidebar")
    public MainSidebar openMainSidebar() {
        if (!isVisible("//div[contains(@class, 'side-menu__body')]")) {
            $x("//rz-mobile-user-menu/button").click();
        }
        waitTillPreloaderInvisible();
        return new MainSidebar();
    }

    @Step("Header: start logging")
    public LogInModal startLogging() {
        return new LogInModal().open();
    }

    @Step("Header: search for {product}")
    public SearchResultsPage search(String product) {
        $(By.name(searchFieldCss)).val(product).pressEnter();
        return new SearchResultsPage();
    }

    @Step("Header: open shopping cart modal")
    public ShoppingCartModal openShoppingCartModal() {
        $x("//button[@rzopencart='']").click();
        $x("//rz-shopping-cart").shouldBe(visible, ofSeconds(20));
        return new ShoppingCartModal();
    }

    public boolean isShoppingCartCounterVisible() {
        return isVisible("//button[@rzopencart='']//span[contains(@class, 'counter')]", 3);
    }

    public boolean isComparisonListCounterVisible() {
        return isVisible("//rz-comparison//rz-icon-counter", 2);
    }

    @Step("Header: open home page via logo")
    public HomePage openHomePageViaLogo() {
        $x(("//a[@class='header__logo']")).click();
        return new HomePage();
    }

    @Step("Header: open catalog modal")
    public CatalogModal openCatalogModal() {
        actions().click($x("//button[@id='fat-menu']")).perform();
        return new CatalogModal();
    }

    @Step("Header: change language to {language}")
    public Header changeLanguage(Language language) {
        $x(format("//li[contains(@class, 'lang__item')]//*[text()=' %s ']", language)).click();
        return new Header();
    }

    public boolean isLanguageSelected(Language language) {
        return isVisible(format("(//li[contains(@class, 'lang__item')]/span[contains(text(),'%s')])[1]", language));
    }

    @Step("Header: open search menu")
    public SearchMenu openSearchMenu() {
        $(By.name(searchFieldCss)).click();
        return new SearchMenu();
    }

    public int getComparisonListProductQuantity() {
        return Integer.parseInt(getText("//rz-comparison//rz-icon-counter"));
    }

    @Step("Header: open comparison list modal")
    public ComparisonListModal openComparisonListModal() {
        $x("//rz-comparison//button").click();
        return new ComparisonListModal();
    }

    @Step("Header: open my orders page")
    public MyOrdersPage openMyOrdersPage() {
        $x("//li[contains(@class,'user')]//a").click();
        return new MyOrdersPage();
    }

    public boolean isOrderIconVisible() {
        return isVisible("//a[contains(@href,'orders') and contains(@class,'header')]");
    }

    public boolean isHeaderLogInButtonVisible() {
        return isVisible("//rz-user");
    }

    public boolean isWishlistCounterVisible() {
        return isVisible("//rz-wishlist//rz-icon-counter", 2);
    }

    public int getWishlistProductQuantity() {
        return Integer.parseInt(getText("//rz-wishlist//rz-icon-counter"));
    }

    @Step("Header: open wishlist page")
    public WishlistPage openWishlistPage() {
        $x("//rz-wishlist//a").click();
        return new WishlistPage();
    }
}