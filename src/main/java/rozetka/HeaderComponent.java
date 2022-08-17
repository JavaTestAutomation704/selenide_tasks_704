package rozetka;

import org.openqa.selenium.By;
import rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class HeaderComponent {
    private final String authorizationXpath = "//button[contains(@class,'side-menu__auth-button')]";

    public HeaderComponent logInViaUserIcon() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }

    public boolean isAuthorizationButtonVisible() {
        return isVisible("(" + authorizationXpath + ")[1]");
    }

    public boolean isRegistrationButtonVisible() {
        return isVisible("(" + authorizationXpath + ")[2]");
    }

    public boolean isLocationSelectionVisible() {
        return isVisible("//button[contains(@class,'city-toggle')]");
    }

    public boolean isHelpCenterButtonVisible() {
        return isVisible("//a[contains(@class,'side-menu__button') and contains(@href,'help.')]");
    }

    public boolean isContactUsButtonVisible() {
        return isVisible("//a[contains(@class,'side-menu__button') and contains(@href,'t.me')]");
    }

    public SearchResultsPage search(String product) {
        $(By.name("search")).val(product).pressEnter();
        return new SearchResultsPage();
    }

    public ShoppingCartModal openShoppingCartViaHeader() {
        return new ShoppingCartModal();
    }

    public String getCurrentCity(){
        return "";
    }

    public HeaderComponent changeCity(String city){
        return this;
    }
}