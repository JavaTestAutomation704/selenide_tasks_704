package rozetka;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ProductPage extends HeaderComponent {

    public ProductPage buyOnCredit() {
        $x("//app-product-credit").click();
        return this;
    }

    public boolean isCreditVisible() {
        return isVisible("//credit-modal");
    }
}