package dankomax;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static dankomax.utils.WebElementUtils.isVisible;
import static dankomax.utils.WebElementUtils.getCollectionSize;


public class SearchResultsPage {
    private final String searchResultsXpath = "//div[contains(@class, 'g ') or @class='g']/div";
    private final String googleLogoXpath = "//a[@id='logo']";

    public HomePage openHomePage() {
        $x(googleLogoXpath).click();
        return new HomePage();
    }

    public SearchResultsPage openSearchResultsPage(int number) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + number + "')]").click();
        return this;
    }

    public SearchResultsPage search(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
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
        return getCollectionSize($$x("//div[contains(@class, 'g ') or @class='g']/div"));
    }

    public boolean isNextPageLinkVisible() {
        return isVisible($x("//a[@id='pnnext']"));
    }

    public boolean isPreviousPageLinkVisible() {
        return isVisible($x("//a[@id='pnprev']"));
    }

    public boolean isGoogleLogoVisible() {
        return isVisible($x(googleLogoXpath));
    }
}