package mvynokur;

import org.openqa.selenium.By;
import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private final String paginationBlockXpath = "//tr[@jsname = 'TeSSVd']";
    private final String headerXpath = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public GooglePage openHomePageViaLogo() {
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
        return WebElementUtil.getCollectionSize(headerXpath);
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
        return WebElementUtil.isVisible("//div[@class = 'logo']");
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtil.isVisible(paginationBlockXpath + "//a/span[contains(text(), 'Previous')]");
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtil.isVisible(paginationBlockXpath + "//a/span[contains(text(), 'Next')]");
    }
}