package ykireyeva;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchPage {

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public GoogleSearchPage titleShouldHave(String expectedTitle) {
        $x("//title").shouldHave(Condition.exactOwnText(expectedTitle));
        return this;
    }

    public GoogleSearchPage gmailLinkShouldBe(Condition condition) {
        $x("//a[text()='Gmail']").shouldBe(condition);
        return this;
    }

}
