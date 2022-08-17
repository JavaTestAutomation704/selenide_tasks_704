package rozetka;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends ResultsFilterComponent {
    String productXpath = "(//div[contains(@class, 'goods-tile ')])[%d]";

    public ProductPage open(int productNumber){
        $x(String.format(productXpath, productNumber)).click();
        return new ProductPage();
    }
}