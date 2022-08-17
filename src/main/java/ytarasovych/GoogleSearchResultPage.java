package ytarasovych;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.getCollectionSize;
import static utils.WebElementUtil.isVisible;

public class GoogleSearchResultPage {
    private final String inputFieldXpath = "//input[@class='gLFyf gsfi']";

    public GooglePage openHomePageViaLogo() {
        $x("//img[@alt='Google']").click();
        return new GooglePage();
    }

    public GoogleSearchResultPage openPage(int pageNumber) {
        $x("//a[@aria-label='Page " + pageNumber + "']").click();
        return this;
    }

    public GoogleSearchResultPage search(String searchTerm) {
        $x(inputFieldXpath)
                .setValue(searchTerm)
                .pressEnter();
        return this;
    }

    public GoogleSearchResultPage clearSearchField() {
        $x(inputFieldXpath).clear();
        return this;
    }

    public String getLinkText(int linkNumber) {
        return $x("(//div[@id='rso']/div)[" + linkNumber + "]")
                .getText()
                .toLowerCase();
    }

    public String getUrl(int linkNumber) {
        return $x("(//div[@id='rso']//h3/ancestor::a)[" + linkNumber + "]").getAttribute("href");
    }

    public int getLinksAmount() {
        return getCollectionSize("//div[@id='rso']/div");
    }

    public boolean isGoogleLogoVisible() {
        return isVisible("//img[@alt='Google']");
    }

    public boolean isNextLinkVisible() {
        return isVisible("//a[@id='pnnext']");
    }

    public boolean isPreviousLinkVisible() {
        return isVisible("//a[@id='pnprev']");
    }
}