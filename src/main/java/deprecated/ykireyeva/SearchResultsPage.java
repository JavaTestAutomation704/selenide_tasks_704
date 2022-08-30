package deprecated.ykireyeva;

import com.softserveinc.ita.rozetka.utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

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
        $x(String.format("//a[contains(@aria-label, 'Page %d')]", number)).click();
        return this;
    }

    public String getResultLinkAttributeValue(int number, String attributeName) {
        return $x(String.format("(//a[descendant::h3])[%d]", number)).getAttribute(attributeName);
    }

    public String getLinkText(int number) {
        return $x(String.format("//a//h3[%d]", number)).getOwnText().toLowerCase();
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
