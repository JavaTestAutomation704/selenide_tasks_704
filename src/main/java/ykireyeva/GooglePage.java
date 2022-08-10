package ykireyeva;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static ykireyeva.utils.WebElementUtils.isElementDisplayed;

public class GooglePage {

    public GooglePage openSearchPage() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public String getPageTitleText() {
        return $x("//title").getOwnText();
    }

    public boolean isGmailLinkDisplayed() {
        return isElementDisplayed("//a[text()='Gmail']");
    }
}
