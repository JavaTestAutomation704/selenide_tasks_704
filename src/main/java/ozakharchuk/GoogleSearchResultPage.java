package ozakharchuk;

import com.codeborne.selenide.CollectionCondition;
import ozakharchuk.utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    private final String nextPageXpath = "//a[@id='pnnext']";
    private final String googleLogoXpath = "//div[@class='logo']/a";
    private final String resultLinksXpath = "//div//a/h3/..";

    public GoogleSearchResultPage searchText(String text) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(text).pressEnter();
        return this;
    }

    public String getResultName(int linkNumber) {
        return $x("(//div//a/h3)["+ linkNumber + "]").text().toLowerCase();
    }

    public String getResultUrl(int linkNumber) {
        return $x("(" + resultLinksXpath + ")[" + linkNumber + "]").getAttribute("href");
    }

    public int getResultLinksNumber() {
        try {
            return $$x(resultLinksXpath).shouldHave(CollectionCondition.sizeGreaterThan(1)).size();
        }
        catch (AssertionError e){
            return 0;
        }
    }

    public GooglePage openGooglePage() {
        $x(googleLogoXpath).click();
        return new GooglePage();
    }

    public boolean isGoogleLogoVisible() {
        return WebElementUtils.isElementVisible(googleLogoXpath);
    }

    public GoogleSearchResultPage openResultPage(int pageNumber) {
        $x("(//div[@role='navigation']//td)" + "[" + pageNumber + "]").click();
        return this;
    }

    public GoogleSearchResultPage openNextResultPage() {
        $x(nextPageXpath).click();
        return this;
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtils.isElementVisible(nextPageXpath);
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtils.isElementVisible("//a[@id='pnprev']");
    }
}
