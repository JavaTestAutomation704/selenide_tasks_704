package ykireyeva;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultsPage {
    private final String logoXpath = "//div[@class='logo']";

    private ElementsCollection getResultLinks() {
        return $$x("(//a[descendant::h3])").filter(Condition.interactable);
    }

    private ElementsCollection getResultLinksTitles() {
        return $$x("//a//h3").filter(Condition.interactable);
    }

    public GoogleSearchPage openGoogleSearchPage() {
        $x(logoXpath).click();
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

    public int getResultLinksSize() {
        return getResultLinks().size();
    }

    public GoogleSearchResultsPage goToPage(int pageNumber) {
        $$x("//a[contains(@aria-label, 'Page')]").get(pageNumber - 2).click();
        return this;
    }

    public void openLink(int linkNumber) {
        getResultLinks().get(linkNumber - 1).click();
    }

    public String getAttributeValueOfResultLink(int linkNumber, String nameOfAttribute) {
        return getResultLinks().get(linkNumber - 1).getAttribute(nameOfAttribute);
    }

    public String getLinkOwnText(int linkNumber) {
        return getResultLinksTitles().get(linkNumber).getOwnText().toLowerCase();
    }

    public boolean logoIsDisplayed() {
        try {
            return $x(logoXpath).isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean nextPageButtonIsDisplayed() {
        try {
            return $x("//a[@id='pnnext']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean prevPageButtonIsDisplayed() {
        try {
            return $x("//a[@id='pnprev']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }
}
