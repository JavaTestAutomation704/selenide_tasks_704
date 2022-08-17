package rozetka;

import org.openqa.selenium.By;
import rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }
}