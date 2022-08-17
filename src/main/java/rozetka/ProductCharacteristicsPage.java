package rozetka;

import static com.codeborne.selenide.Selenide.$x;

public class ProductCharacteristicsPage extends HeaderComponent {

    public String getTitle() {
        return $x("//h1[@class='product__title']").text();
    }
}