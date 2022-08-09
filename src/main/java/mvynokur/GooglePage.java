package mvynokur;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    private final String logoIconXpath = "//div[@class = 'logo']";
    private final String paginationBlockXpath = "//tr[@jsname = 'TeSSVd']";

    public GooglePage openHomePage() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public GooglePage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return this;
    }

    public boolean isLogoVisible() {
        try {
            return $x(logoIconXpath).isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean isPreviousPageVisible() {
        try {
            return $x(paginationBlockXpath + "//a/span[contains(text(), 'Previous')]").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean isNextPageVisible() {
        try {
            return $x(paginationBlockXpath + "//a/span[contains(text(), 'Next')]").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean isFeelingLuckyButtonVisible() {
        try {
            return $x("//div[@class='FPdoLc lJ9FBc']//input[@name='btnI']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean isLanguageBlockVisible() {
        try {
            return $x("//div[@id='SIvCob']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public GooglePage clearSearchField() {
        $(By.name("q")).clear();
        return this;
    }

    public GooglePage navigateToHomePage() {
        $x(logoIconXpath).click();
        return this;
    }

    public GooglePage navigateToPage(int index) {
        $x(paginationBlockXpath + "//a[contains(@aria-label, '" + index + "')]").click();
        return this;
    }
}
