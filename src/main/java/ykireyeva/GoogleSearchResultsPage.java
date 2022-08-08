package ykireyeva;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultsPage {
    private final ElementsCollection resultLinksTitles = $$x("//a//h3").filter(Condition.interactable);
    private final ElementsCollection resultLinks = $$x("(//a[descendant::h3])").filter(Condition.interactable);
    private final String logoXPathExp = "//div[@class='logo']";

    public GoogleSearchPage openGoogleSearchPage() {
        $x(logoXPathExp).click();
        return new GoogleSearchPage();
    }

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return this;
    }

    public GoogleSearchResultsPage clearSearchField() {
        $x("//div[contains(@class, 'M2vV3')]").click();
        return this;
    }

    public GoogleSearchResultsPage submitButtonOnSearchFieldShouldNot(Condition condition) {
        $x("//button[@type='submit']").shouldNot(condition);
        return this;
    }

    public GoogleSearchResultsPage googleLogoShouldBe(Condition condition) {
        $x(logoXPathExp).shouldBe(condition);
        return this;
    }

    public GoogleSearchResultsPage nextPageButtonShouldBe(Condition condition) {
        $x("//a[@id='pnnext']").shouldBe(condition);
        return this;
    }

    public GoogleSearchResultsPage prevPageButtonShouldBe(Condition condition) {
        $x("//a[@id='pnprev']").shouldBe(condition);
        return this;
    }

    public GoogleSearchResultsPage goToPage(int pageNumber) {
        $$x("//a[contains(@aria-label, 'Page')]").get(pageNumber - 2).click();
        return this;
    }

    public void openLink(int linkNumber) {
        resultLinks.get(linkNumber - 1).click();
    }

    public GoogleSearchResultsPage resultLinksShouldHaveSizeGreaterThanOrEqual(int size) {
        resultLinks.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(size));
        return this;
    }

    public GoogleSearchResultsPage resultLinkShouldHaveOwnText(int linkNumber, String expectedOwnText) {
        resultLinksTitles.get(linkNumber).shouldHave(Condition.ownText(expectedOwnText));
        return this;
    }

    public GoogleSearchResultsPage resultLinkShouldNotHaveOwnText(int linkNumber, String expectedOwnText) {
        resultLinksTitles.get(linkNumber).shouldNotHave(Condition.ownText(expectedOwnText));
        return this;
    }

    public String getAttributeValueOfResultLink(int linkNumber, String nameOfAttribute) {
        return resultLinks.get(linkNumber - 1).getAttribute(nameOfAttribute);
    }
}
