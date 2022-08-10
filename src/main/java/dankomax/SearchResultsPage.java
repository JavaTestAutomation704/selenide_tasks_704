package dankomax;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class SearchResultsPage {
    private final String googleLogoXpath = "//a[@id='logo']";

    public HomePage openHomePage() {
        $x(googleLogoXpath).click();
        return new HomePage();
    }

    public SearchResultsPage openSearchResultsPage(int pageNumber) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + pageNumber + "')]").click();
        return this;
    }

    public SearchResultsPage searchPhrase(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
        return this;
    }

    public String getSearchResultTitleText(int resultNumber) {
        return searchResultTitle(resultNumber).text();
    }

    public String getSearchResultLinkHrefValue(int resultNumber) {
        return searchResultTitle(resultNumber).ancestor("a").attr("href");
    }

    public String getSearchResultText(int resultNumber) {
        return searchResultTitle(resultNumber).ancestor("div[contains(@class, 'g ')]").text();
    }

    public int getSearchResultCollectionSize() {
        return $$x("//a/h3/ancestor::div[contains(@class, 'g ')]").size();
    }

    public boolean isNextLinkVisible() {
        return $x("//a[@id='pnnext']").isDisplayed();
    }

    public boolean isPreviousLinkVisible() {
        return $x("//a[@id='pnprev']").isDisplayed();
    }

    public boolean isGoogleLogoVisible() {
        return $x(googleLogoXpath).isDisplayed();
    }

    private SelenideElement searchResultTitle(int resultNumber) {
        return $x(String.format("(//div[contains(@class, 'g ')]//a//h3)[%d]", resultNumber));
    }
}