package deprecated.ykireyeva;

import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class SearchResultsPage {
    private final String logoXpath = "//div[@class='logo']";
    private final String searchFieldXpath = "//input[@name='q']";

    public GooglePage openGooglePageViaLogo() {
        $x(logoXpath).click();
        return new GooglePage();
    }

    public SearchResultsPage search(String query) {
        $x(searchFieldXpath).setValue(query).pressEnter();
        return this;
    }

    public SearchResultsPage clearSearchField() {
        $x(searchFieldXpath).clear();
        return this;
    }

    public int getResultLinksSize() {
        return WebElementUtil.getCollectionSize("(//a[descendant::h3])");
    }

    public SearchResultsPage openPage(int number) {
        $x("//a[contains(@aria-label, 'Page " + number + "')]").click();
        return this;
    }

    public String getResultLinkAttributeValue(int number, String attributeName) {
        return $x("(//a[descendant::h3])[" + number + "]").getAttribute(attributeName);
    }

    public String getLinkText(int number) {
        return $x("//a//h3[" + number + "]").getOwnText().toLowerCase();
    }

    public boolean isLogoVisible() {
        return isVisible(logoXpath);
    }

    public boolean isNextPageLinkVisible() {
        return isVisible("//a[@id='pnnext']");
    }

    public boolean isPreviousPageLinkVisible() {
        return isVisible("//a[@id='pnprev']");
    }
}
