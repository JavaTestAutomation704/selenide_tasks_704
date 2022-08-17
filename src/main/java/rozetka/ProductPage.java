package rozetka;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends HeaderComponent {

    public ProductPage buyOnCredit() {
        $x("//app-product-credit").click();
        return this;
    }
}