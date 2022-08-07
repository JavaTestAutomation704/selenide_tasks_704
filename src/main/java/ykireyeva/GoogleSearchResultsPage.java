package ykireyeva;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultsPage {

    public ElementsCollection getResultLinks() {
        return $$x("(//a[descendant::h3])").filter(Condition.interactable);
    }

    public ElementsCollection getResultLinksTitles() {
        return $$x("//a//h3").filter(Condition.interactable);
    }

    public SelenideElement getSubmitButtonOnSearchField() {
        return $x("//button[@type='submit']");
    }

    public ElementsCollection getPageNumbersToOpen() {
        return $$x("//a[contains(@aria-label, 'Page')]");
    }

    public SelenideElement getClearSearchFieldButton() {
        return $x("//div[contains(@class, 'M2vV3')]");
    }

    public SelenideElement getGoogleLogo() {
        return $x("//div[@class='logo']");
    }

    public SelenideElement getNextPageButton() {
        return $x("//a[@id='pnnext']");
    }

    public SelenideElement getPrevPageButton() {
        return $x("//a[@id='pnprev']");
    }

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return this;
    }
}
