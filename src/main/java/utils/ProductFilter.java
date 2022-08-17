package utils;

public enum ProductFilter {

    AVAILABLE("Є в наявності"),
    RUN_OUT("Немає в наявності");

    private final String filterXpath;

    ProductFilter(String filterXpath) {
        this.filterXpath = filterXpath;
    }
}
