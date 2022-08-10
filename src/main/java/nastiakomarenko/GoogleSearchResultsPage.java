package nastiakomarenko;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;


public class GoogleSearchResultsPage {

    public GoogleSearchResultsPage clearInputField() {
        $x("//input[@name='q']").clear();
        return this;
    }

    public GoogleSearchResultsPage search(String searchItem) {
        $x("//input[@name='q']").setValue(searchItem).pressEnter();
        return this;
    }

    public String getLinkNumberText(int linkNumber) {
        return $x(String.format("(//a/h3)[%s]", linkNumber)).text();
    }
    public String logoXpath = "//div[@class = 'logo']";


    public GoogleSearchResultsPage goToGoogleLogo() {
        $x(logoXpath).click();
        return this;
    }

    public GoogleSearchResultsPage goToPage(int page) {
        $x((String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%s')]", page)))
                .click();
        return this;

    }
    public int getNumberOfAllLinks(){
        try {
            $$x("(//a)").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        } catch (AssertionError e) {
            throw new AssertionError(e);
        }
        return $$x("(//a)").size();
    }

    public String getPreviousPageXpath() {
        return "//a[@id='pnprev']";
    }

    public String nextPageXpath = "//a[@id='pnnext']";

}