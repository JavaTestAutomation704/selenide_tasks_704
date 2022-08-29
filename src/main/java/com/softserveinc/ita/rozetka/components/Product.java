package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.Availability;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class Product {
    private final String productXpath;
    private final String titleXpath = "//span[@class='goods-tile__title']";

    public Product(int productNumber) {
        this.productXpath = String.format("(//rz-catalog-tile)[%d]", productNumber);
    }

    public Product(String productNumber) {
        if (productNumber.equals("last")) {
            this.productXpath = "(//rz-catalog-tile)[last()]";
        } else {
            this.productXpath = String.format("(//rz-catalog-tile)[%s]", productNumber);
        }
    }

    public String getTitle() {
        return getText(productXpath + titleXpath).toLowerCase();
    }

    public long getPrice() {
        return getLong(productXpath + "//span[@class='goods-tile__price-value']");
    }

    public SearchResultsPage addToShoppingCart() {
        $x(productXpath + "//button[contains(@class, 'buy-button')]").click();
        return new SearchResultsPage();
    }

    public ProductPage open() {
        $x(productXpath + titleXpath).click();
        $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .hover();
        return new ProductPage();
    }

    public boolean isUsed() {
        return isVisible(productXpath + "//span[contains(@class, 'promo-label_type_used')]");
    }

    public Availability getAvailability() {
        String availability = getText(productXpath + "//div[contains(@class, 'availability')]");
        return Availability.getByValue(availability);
    }

    public boolean isAvailable() {
        Availability availability = getAvailability();
        return availability == Availability.AVAILABLE
                || availability == Availability.READY_TO_BE_DELIVERED
                || availability == Availability.RUNNING_OUT_OF_STOCK;
    }

    public boolean isAddedToShoppingCart(){
        return isVisible(productXpath + "//button[contains(@class, 'buy-button_state_in-cart')]");
    }
}