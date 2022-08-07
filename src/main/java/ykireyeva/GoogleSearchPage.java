package ykireyeva;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchPage {
    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public SelenideElement getGmailLink() {
        return $x("//a[text()='Gmail']");
    }

    public SelenideElement getPageTitle() {
        return $x("//title");
    }

}
