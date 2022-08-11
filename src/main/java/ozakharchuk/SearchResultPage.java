package ozakharchuk;

import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private final String nextPageXpath = "//a[@id='pnnext']";
    private final String googleLogoXpath = "//div[@class='logo']/a";
    private final String resultLinksXpath = "//div//a/h3/..";

    public SearchResultPage searchFor(String text) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(text).pressEnter();
        return this;
    }

    public String getResultName(int linkNumber) {
        return $x("(//div//a/h3)[" + linkNumber + "]").text().toLowerCase();
    }

    public String getResultUrl(int linkNumber) {
        return $x("(" + resultLinksXpath + ")[" + linkNumber + "]").getAttribute("href");
    }

    public int getLinksAmount() {
        return WebElementUtil.getCollectionSize(resultLinksXpath);
    }

    public GooglePage openHomePageViaLogo() {
        $x(googleLogoXpath).click();
        return new GooglePage();
    }

    public boolean isGoogleLogoVisible() {
        return WebElementUtil.isElementVisible(googleLogoXpath);
    }

    public SearchResultPage openPage(int number) {
        $x("(//div[@role='navigation']//td)" + "[" + number + "]").click();
        return this;
    }

    public SearchResultPage openNextPage() {
        $x(nextPageXpath).click();
        return this;
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtil.isElementVisible(nextPageXpath);
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtil.isElementVisible("//a[@id='pnprev']");
    }
}
