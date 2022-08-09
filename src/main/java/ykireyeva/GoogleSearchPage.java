package ykireyeva;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchPage {

    public GoogleSearchPage openGoogleSearchPage() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public GoogleSearchResultsPage search(String query) {
        $x("//input[@name='q']").setValue(query).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public String getTitleOwnText() {
        return $x("//title").getOwnText();
    }

    public boolean gmailLinkIsDisplayed() {
        try {
            return $x("//a[text()='Gmail']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean submitButtonOnSearchFieldIsDisplayed() {
        try {
            return $x("//button[@type='submit']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }
}
