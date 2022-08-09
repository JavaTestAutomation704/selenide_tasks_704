package ykireyeva;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.NoSuchElementException;

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

    public boolean gmailLinkIsEnabled() {
        try {
            return $x("//a[text()='Gmail']").isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean submitButtonOnSearchFieldIsEnabled() {
        try {
            return $x("//button[@type='submit']").exists();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
