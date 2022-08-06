package dankomax;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class SearchPage {
    private final String searchResultContainer = "div[contains(@class, 'g ')]";

    public SelenideElement searchResultTitleByNumber(int titleNumber) {
        return $x(String.format("(//%s//a//h3)[%d]", searchResultContainer, titleNumber));
    }

    public SelenideElement searchResultLinkByNumber(int titleNumber) {
        return searchResultTitleByNumber(titleNumber).ancestor("a");
    }

    public SelenideElement searchResultByNumber(int titleNumber) {
        return searchResultTitleByNumber(titleNumber).ancestor(searchResultContainer);
    }

    public ElementsCollection searchResultCollection() {
        return $$x("//a/h3/ancestor::" + searchResultContainer);
    }

    public SearchPage paginationOpenPage(int page) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + page + "')]").click();
        return this;
    }

    public SelenideElement paginationNextButton() {
        return $x("//a[@id='pnnext']");
    }

    public SelenideElement paginationPreviousButton() {
        return $x("//a[@id='pnprev']");
    }

    public SelenideElement googleLogo() {
        return $x("//a[@id='logo']");
    }

    public HomePage openHomePage() {
        googleLogo().click();
        return new HomePage();
    }
}