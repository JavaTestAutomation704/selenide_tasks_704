package rozetka;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends ResultsFilterComponent {
    String productXpath = "(//div[contains(@class, 'goods-tile ')])[%d]";

    public ProductPage open(int productNumber) {
        $x(String.format(productXpath, productNumber)).click();
        return new ProductPage();
    }

    public int getResultsAmount() {
        return Integer.parseInt(
                $x("//p[contains(@class, 'selection')]")
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    public SearchResultsPage resetFilers() {
        $x("//button[contains(@class, 'reset')]").click();
        return this;
    }

    public SearchResultsPage addToCart(int product) {
        return this;
    }

    public List<String> getProductStatuses() {
        return new ArrayList<>();
    }

    public List<String> getProductTitles() {
        return new ArrayList<>();
    }

    public String getTitle(int product) {
        return $x(String.format("(//span[@class='goods-tile__title'])[%d]", product)).text();
    }

    public int getProductPrice(int number) {
        return 0;
    }

    public boolean isProductsOnSale() {
        return false;
    }

    public boolean areAllProductsPreUsed() {
        return true;
    }
}