package dankomax;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class SearchPage {
    private final String searchResultContainer = "div[contains(@class, 'g ')]";
    private final String googleLogo = "//a[@id='logo']";

    private SelenideElement searchResultTitleByNumber(int titleNumber) {
        return $x(String.format("(//%s//a//h3)[%d]", searchResultContainer, titleNumber));
    }

    public SearchPage verifySearchResultTitleHasExpectedText(int titleNumber, String expected) {
        searchResultTitleByNumber(titleNumber).shouldHave(text(expected));
        return this;
    }

    public SearchPage verifyValidityOfSearchResultLinkByNumber(int titleNumber) {
        searchResultTitleByNumber(titleNumber).ancestor("a")
                .shouldHave(attributeMatching("href", "^((ftp|http|https)://)?www\\..*"));
        return this;
    }

    public SearchPage verifySearchResultsQuantityIsGreaterOrEqual(int resultsQuantity) {
        $$x("//a/h3/ancestor::" + searchResultContainer).shouldBe(sizeGreaterThanOrEqual(9));
        return this;
    }

    public SearchPage verifySearchResultByNumberIncludeText(int titleNumber, String includedText) {
        searchResultTitleByNumber(titleNumber).ancestor(searchResultContainer).shouldHave(text(includedText));
        return this;
    }

    public SearchPage verifySearchResultByNumberExcludeText(int titleNumber, String excludedText) {
        searchResultTitleByNumber(titleNumber).ancestor(searchResultContainer).shouldNotHave(text(excludedText));
        return this;
    }

    public SearchPage paginationOpenPage(int page) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + page + "')]").click();
        return this;
    }

    public SearchPage checkPaginationNextButtonIsVisible() {
        $x("//a[@id='pnnext']").shouldBe(visible);
        return this;
    }

    public SearchPage checkPaginationPreviousButtonIsVisible() {
        $x("//a[@id='pnprev']").shouldBe(visible);
        return this;
    }

    public SearchPage checkGoogleLogoIsVisible() {
        $x(googleLogo).shouldBe(visible);
        return this;
    }

    public HomePage openHomePage() {
        $x(googleLogo).click();
        return new HomePage();
    }
}