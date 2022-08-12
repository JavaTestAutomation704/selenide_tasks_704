package dankomax;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;
import static utils.WebElementUtil.getCollectionSize;


public class SearchResultsPage {
    private final String searchResultsXpath = "//div[contains(@class, 'g ') or @class='g']/div";
    private final String googleLogoXpath = "//a[@id='logo']";

    public HomePage openHomePageViaLogo() {
        $x(googleLogoXpath).click();
        return new HomePage();
    }

    public SearchResultsPage open(int page) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + page + "')]").click();
        return this;
    }

    public SearchResultsPage search(String phrase) {
        SelenideElement searchField = $x("//input[@name='q']");
        searchField.clear();
        searchField.setValue(phrase).pressEnter();
        return this;
    }

    public String getTitleText(int result) {
        return $x("(" + searchResultsXpath + "//a/h3)[" + result + "]").text().toLowerCase();
    }

    public String getLinkUrl(int result) {
        return $x("(" + searchResultsXpath + "//a/h3/parent::a)[" + result + "]").attr("href");
    }

    public String getText(int result) {
        return $x("(" + searchResultsXpath + ")[" + result + "]").text().toLowerCase();
    }

    public int getResultsQuantity() {
        return getCollectionSize("//div[contains(@class, 'g ') or @class='g']/div");
    }

    public boolean isNextPageLinkVisible() {
        return isVisible("//a[@id='pnnext']");
    }

    public boolean isPreviousPageLinkVisible() {
        return isVisible("//a[@id='pnprev']");
    }

    public boolean isGoogleLogoVisible() {
        return isVisible(googleLogoXpath);
    }
}