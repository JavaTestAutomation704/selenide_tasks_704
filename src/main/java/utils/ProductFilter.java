package utils;

public enum ProductFilter {

    AVAILABLE("Є в наявності"),
    OUT_OF_STOCK("Немає в наявності");

    private final String filterXpath;

    ProductFilter(String filterXpath) {
        this.filterXpath = filterXpath;
    }

    public String getFilterXpath() {
        return filterXpath;
    }
}
