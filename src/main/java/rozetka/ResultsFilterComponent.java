package rozetka;

import utils.ProductFilter;

import static com.codeborne.selenide.Selenide.$x;

public class ResultsFilterComponent extends HeaderComponent {

    public SearchResultsPage toggleFilter(ProductFilter... type) {
        for (ProductFilter filter : type) {
            $x(String.format("//a[@data-id = '%s']", filter)).click();
        }
        return new SearchResultsPage();
    }
}