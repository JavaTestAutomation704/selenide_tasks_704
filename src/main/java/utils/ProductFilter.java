package utils;

public enum ProductFilter {

    AVAILABLE("Є в наявності"),
    RUN_OUT("Немає в наявності"),
    WHITE_COLOR("Білий"),
    ROZETKA_SELLER("Rozetka"),
    OTHER_SELLERS("Інші продавці");

    private final String filterXpath;

    ProductFilter(String filterXpath) {
        this.filterXpath = filterXpath;
    }

    public String getFilterXpath() {
        return filterXpath;
    }
}
