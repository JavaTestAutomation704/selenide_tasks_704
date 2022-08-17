package rozetka;

import rozetka.modals.ShoppingCartModal;

public class HeaderComponent {

    public SearchResultsPage search() {
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }
}