package ykireyeva;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class GooglePage {

    public GooglePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public SearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return new SearchResultsPage();
    }

    public String getPageTitleText() {
        return $x("//title").getOwnText();
    }

    public boolean isGmailLinkVisible() {
        return isVisible("//a[text()='Gmail']");
    }
}
