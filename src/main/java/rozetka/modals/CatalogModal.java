package rozetka.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CatalogModal {

    @FindBy(xpath = "//ul[@class = 'menu-categories ng-star-inserted']")
    private WebElement categoriesBlock;

    @FindBy(xpath = "//div[@class='menu-wrapper menu-wrapper_state_animated']")
    private List<WebElement> categoriesNameList;

    public boolean categoriesBlockIsDisplayed() {
        try {
            return categoriesBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isCategoriesListActivated() {
        for (WebElement categoryElement : categoriesNameList) {
            try {
                categoryElement.isDisplayed();
                categoryElement.isEnabled();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}