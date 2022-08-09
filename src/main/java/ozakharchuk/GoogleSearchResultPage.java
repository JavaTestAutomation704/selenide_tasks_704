package ozakharchuk;

import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    String nextPageXpath = "//a[@id='pnnext']";
    String googleLogoXpath = "//div[@class='logo']/a";
    String resultLinksXpath = "//div//a/h3/..";

    public String getResultLinkName(int numberOfLink) {
        return $$x("//div//a/h3").get(numberOfLink).text();
    }

    public String getResultLinkUrl(int numberOfLink) {
        return $$x(resultLinksXpath).get(numberOfLink).getAttribute("href");
    }

    public void openLink(int numberOfLink) {
        $$x(resultLinksXpath).get(numberOfLink).click();
    }

    public int getResultLinksNumber() {
        return $$x(resultLinksXpath).size();
    }

    public GoogleHomePage openHomePage() {
        $x(googleLogoXpath).click();
        return new GoogleHomePage();
    }

    public boolean isGoogleLogoVisible() {
        try {
            return $x(googleLogoXpath).isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public GoogleSearchResultPage chooseNumberOfResultPage(int number) {
        $$x("//div[@role='navigation']//td").get(number).click();
        return this;
    }

    public GoogleSearchResultPage openNextResultPage() {
        $x(nextPageXpath).click();
        return this;
    }

    public boolean isNextPageButtonVisible() {
        try {
            return $x(nextPageXpath).isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public boolean isPreviousPageButtonVisible() {
        try {
            return $x("//a[@id='pnprev']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }
}
