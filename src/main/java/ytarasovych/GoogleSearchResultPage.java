package ytarasovych;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;
import static ytarasovych.utils.SelenideElementUtils.isElementDisplayed;

public class GoogleSearchResultPage {
    private final String inputFieldXpath = "//input[@class='gLFyf gsfi']";

    public GooglePage openGooglePage() {
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
        return $x("(//div[@id='rso']//h3)[" + linkNumber + "]")
                .getText()
                .toLowerCase();
    }

    public String getUrl(int linkNumber) {
        return $x("(//div[@id='rso']//h3/ancestor::a)[" + linkNumber + "]").getAttribute("href");
    }

    public int getLinksAmount() {
        try {
            return $$x("//div[@id='rso']/div")
                    .shouldHave(sizeGreaterThanOrEqual(1), ofSeconds(2))
                    .size();
        } catch (AssertionError e) {
            return 0;
        }
    }

    public boolean isGoogleLogoDisplayed() {
        return isElementDisplayed("//img[@alt='Google']");
    }

    public boolean isNextLinkDisplayed() {
        return isElementDisplayed("//a[@id='pnnext']");
    }

    public boolean isPreviousLinkDisplayed() {
        return isElementDisplayed("//a[@id='pnprev']");
    }
}