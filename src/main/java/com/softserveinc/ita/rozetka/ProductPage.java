package com.softserveinc.ita.rozetka;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-prices__inner ng-star-inserted']")
    private WebElement price;

}