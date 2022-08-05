package mvynokur;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    private final String LogoIcon = "//div[@class = 'logo']";
    private final String PaginationBlock = "//tr[@jsname = 'TeSSVd']";
    private final String PreviousPage = PaginationBlock + "//a/span[contains(text(), 'Previous')]";
    private final String NextPage = PaginationBlock + "//a/span[contains(text(), 'Next')]";
    private final String UkrainianLanguageLink = "//div[@id = 'SIvCob']/a[contains(text(), 'українська')]";

    public SelenideElement getLogoIcon() {
        return $x(LogoIcon);
    }

    public SelenideElement getPreviousPage() {
        return $x(PreviousPage);
    }

    public SelenideElement getNextPage() {
        return $x(NextPage);
    }

    public SelenideElement getUkrainianLanguageLink() {
        return $x(UkrainianLanguageLink);
    }

    public void clearSearchField() {
        $(By.name("q")).clear();
    }

    public void navigateToHomePage() {
        $x(LogoIcon).click();
    }

    public void navigateToPage(int index) {
        $x(PaginationBlock + "//a[contains(@aria-label, '" + index + "')]").click();
    }
}
