package ykireyeva;

import com.codeborne.selenide.SelenideElement;
import ykireyeva.utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$x;
import static ykireyeva.utils.WebElementUtils.isElementDisplayed;

public class GoogleSearchResultsPage {
    private final String logoXpath = "//div[@class='logo']";

    private SelenideElement getResultLink(int number) {
        return $x("(//a[descendant::h3])[" + number + "]");
    }

    private SelenideElement getResultLinkTitle(int number) {
        return $x("//a//h3[" + number + "]");
    }

    private SelenideElement getPage(int number) {
        return $x("//a[contains(@aria-label, 'Page " + number + "')]");
    }

    public GooglePage openGooglePage() {
        $x(logoXpath).click();
        return new GooglePage();
    }

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return this;
    }

    public GoogleSearchResultsPage clearSearchField() {
        $x("//input[@name='q']").clear();
        return this;
    }

    public int getResultLinksSize() {
        return WebElementUtils.getElementsSize("(//a[descendant::h3])");
    }

    public GoogleSearchResultsPage openPage(int number) {
        getPage(number).click();
        return this;
    }

    public String getResultLinkAttributeValue(int number, String attributeName) {
        return getResultLink(number).getAttribute(attributeName);
    }

    public String getLinkText(int number) {
        return getResultLinkTitle(number).getOwnText().toLowerCase();
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logoXpath);
    }

    public boolean isNextPageLinkDisplayed() {
        return isElementDisplayed("//a[@id='pnnext']");
    }

    public boolean isPreviousPageLinkDisplayed() {
        return isElementDisplayed("//a[@id='pnprev']");
    }
}
