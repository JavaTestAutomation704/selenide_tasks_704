package rozetka;

import utils.ProductFilter;

import static com.codeborne.selenide.Selenide.$x;

public class ResultsFilterComponent extends HeaderComponent {

    public SearchResultsPage toggleFilter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type)).click();
        return new SearchResultsPage();
    }
}