package rozetka;

import rozetka.modals.ShoppingCartModal;

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
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }
}