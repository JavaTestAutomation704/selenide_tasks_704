package rozetka;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ProductPage extends HeaderComponent {

    @FindBy(xpath = "//div[@class='product-prices__inner ng-star-inserted']")
    private WebElement price;

    public String getTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public ShoppingCartModal buy() {
        $x("//ul[@class='product-buttons']//button[contains(@class,'buy-button')]").click();
        return new ShoppingCartModal();
    }

    public ProductPage buyOnCredit() {
        $x("//app-product-credit").click();
        return this;
    }

    public boolean isCreditVisible() {
        return isVisible("//credit-modal");
    }
}