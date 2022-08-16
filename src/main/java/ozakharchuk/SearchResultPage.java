package ozakharchuk;

import com.codeborne.selenide.SelenideElement;
import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private final String nextPageXpath = "//a[@id='pnnext']";
    private final String googleLogoXpath = "//div[@class='logo']/a";
    private final String resultLinksXpath = "//div//a/h3/..";

    public SearchResultPage searchFor(String text) {
        SelenideElement searchField = $x("//input[@name='q']");
        searchField.clear();
        searchField.setValue(text).pressEnter();
        return this;
    }

    public String getLinkText(int linkNumber) {
        return $x(String.format("(//div//a/h3)[%s]", linkNumber)).text().toLowerCase();
    }

    public String getLinkUrl(int linkNumber) {
        return $x(String.format("(%s)[%s]", resultLinksXpath, linkNumber)).getAttribute("href");
    }

    public int getLinksAmount() {
        return WebElementUtil.getCollectionSize(resultLinksXpath);
    }

    public GooglePage openHomePageViaLogo() {
        $x(googleLogoXpath).click();
        return new GooglePage();
    }

    public boolean isGoogleLogoVisible() {
        return WebElementUtil.isVisible(googleLogoXpath);
    }

    public SearchResultPage openPage(int number) {
        $x(String.format("(//div[@role='navigation']//td)[%s]", number)).click();
        return this;
    }

    public SearchResultPage openNextPage() {
        $x(nextPageXpath).click();
        return this;
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtil.isVisible(nextPageXpath);
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtil.isVisible("//a[@id='pnprev']");
    }
}
