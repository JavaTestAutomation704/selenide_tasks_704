package mvynokur;

import com.codeborne.selenide.CollectionCondition;
import mvynokur.utils.WebElementUtils;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private final String paginationBlockXpath = "//tr[@jsname = 'TeSSVd']";
    private final String headerXpath = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public GooglePage openHomePage() {
        $x("//div[@class = 'logo']").click();
        return new GooglePage();
    }

    public SearchResultsPage clearSearchField() {
        $(By.name("q")).clear();
        return this;
    }

    public SearchResultsPage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return this;
    }

    public SearchResultsPage openPage(int number) {
        $x(paginationBlockXpath + "//a[contains(@aria-label, '" + number + "')]").click();
        return this;
    }

    public int getLinksAmount() {
        try {
            return $$x(headerXpath).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1), Duration.ofSeconds(5)).size();
        } catch (AssertionError e) {
            return 0;
        }
    }

    public String getName(int linkNumber) {
        return $x("//div[" + linkNumber + "]" + headerXpath + "/parent::a").text().toLowerCase();
    }

    public String getDescriptionText(int linkNumber) {
        return $$x("//div[@class = 'Uroaid']").get(linkNumber - 1).text().toLowerCase();
    }

    public String getUrl(int linkNumber) {
        return $x("//div[" + linkNumber + "]" + headerXpath + "/parent::a").getAttribute("href");
    }

    public boolean isLogoVisible() {
        return WebElementUtils.isElementVisible("//div[@class = 'logo']");
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtils.isElementVisible(paginationBlockXpath + "//a/span[contains(text(), 'Previous')]");
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtils.isElementVisible(paginationBlockXpath + "//a/span[contains(text(), 'Next')]");
    }
}