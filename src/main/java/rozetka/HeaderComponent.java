package rozetka;

import org.openqa.selenium.By;
import rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    public boolean isAuthorizationButtonVisible() {
        return true;
    }

    public boolean isRegistrationButtonVisible() {
        return true;
    }

    public boolean isLocationSelectionVisible() {
        return true;
    }

    public boolean isHelpCenterButtonVisible() {
        return true;
    }

    public boolean isContactUsButtonVisible() {
        return true;
    }

    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }
}