package rozetka;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends ResultsFilterComponent {

    public SearchResultsPage addToCart(int product) {
        return this;
    }

    public List<String> getProductStatuses() {
        return new ArrayList<>();
    }

    public List<String> getProductTitles() {
        return new ArrayList<>();
    }

    public int getProductPrice(int number) {
        return 0;
    }
}