package mvynokur;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    private final String logoIcon = "//div[@class = 'logo']";
    private final String paginationBlock = "//tr[@jsname = 'TeSSVd']";
    private final String previousPage = paginationBlock + "//a/span[contains(text(), 'Previous')]";
    private final String nextPage = paginationBlock + "//a/span[contains(text(), 'Next')]";
    private final String feelingLuckyButton = "//div[@class='FPdoLc lJ9FBc']//input[@name='btnI']";
    private final String languageBlock = "//div[@id='SIvCob']";

    public GooglePage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return new GooglePage();
    }

    public GooglePage logoIconIsVisible() {
        $x(logoIcon).shouldBe(visible);
        return this;
    }

    public GooglePage previousPageIsVisible() {
        $x(previousPage).shouldBe(visible);
        return this;
    }

    public GooglePage nextPageIsVisible() {
        $x(nextPage).shouldBe(visible);
        return this;
    }

    public GooglePage feelingLuckyButtonIsVisible() {
        $x(feelingLuckyButton).shouldBe(visible);
        return this;
    }

    public GooglePage languageBlockIsVisible() {
        $x(languageBlock).shouldBe(visible);
        return this;
    }

    public GooglePage clearSearchField() {
        $(By.name("q")).clear();
        return this;
    }

    public GooglePage navigateToHomePage() {
        $x(logoIcon).click();
        return this;
    }

    public GooglePage navigateToPage(int index) {
        $x(paginationBlock + "//a[contains(@aria-label, '" + index + "')]").click();
        return this;
    }
}
