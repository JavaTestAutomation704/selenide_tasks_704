package deprecated.mvynokur;

import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage {
    private final String searchFieldCss = "q";
    private final String headerXpath = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public GooglePage openHomePageViaLogo() {
        $x("//div[@class = 'logo']").click();
        return new GooglePage();
    }

    public SearchResultsPage clearSearchField() {
        $(By.name(searchFieldCss)).clear();
        return this;
    }

    public SearchResultsPage searchFor(String text) {
        $(By.name(searchFieldCss)).val(text).pressEnter();
        return this;
    }

    public SearchResultsPage openPage(int number) {
        $x(String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%d')]", number)).click();
        return this;
    }

    public int getLinksAmount() {
        return WebElementUtil.getCollectionSize(headerXpath);
    }

    private String getLink(int number) {
        return String.format("//div[%d]%s/parent::a", number, headerXpath);
    }

    public String getName(int linkNumber) {
        return $x(getLink(linkNumber)).text().toLowerCase();
    }

    public String getUrl(int linkNumber) {
        return $x(getLink(linkNumber)).getAttribute("href");
    }

    public String getDescriptionText(int linkNumber) {
        return $x(String.format("//div[@class = 'Uroaid'][%d]", linkNumber)).text().toLowerCase();
    }

    public boolean isLogoVisible() {
        return WebElementUtil.isVisible("//div[@class = 'logo']");
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtil.isVisible("//a[@id = 'pnprev']");
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtil.isVisible("//a[@id = 'pnnext']");
    }
}