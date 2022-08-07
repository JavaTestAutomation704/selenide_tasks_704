package ozakharchuk;

import com.codeborne.selenide.*;
import com.codeborne.selenide.CollectionCondition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    @BeforeClass
    public void downloadBrowser() {
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void openGoogleAndTypeSearchItem() {
        Selenide.open("https://www.google.com/");
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.searchForText("funny dogs");
    }

    @Test
    public void verifyFirstLinkNameContainsDogs() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showResultLinkName(1).shouldHave(text("dog"));
    }

    @Test
    public void verifyNinthLinkValid() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showResultLink().get(9).shouldHave(attribute("href"));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.openHomePage();
        webdriver().shouldHave(urlStartingWith("https://www.google.com/"));
    }

    @Test
    public void verifyFirstLinkNameContainsDogsFifthPage() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.chooseNumberOfResultPage(5).showResultLinkName(1).shouldHave(text("dog"));
    }

    @Test
    public void verifyAtLeastNineLinks() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showResultLink().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyFirstLinkContainsKitten() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.searchForText("funny kitten").showResultLinkName(1).shouldHave(text("kitten"));

    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showGoogleLogo().shouldBe(visible);
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showNextPageLink().shouldBe(visible);
    }

    @Test
    public void verifyNextAndPreviousLinkIsDisplayed() {
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        googleSearchResultPage.showNextPageLink().shouldBe(visible);
        googleSearchResultPage.chooseNumberOfResultPage(4).showPreviousPageLink().shouldBe(visible);
    }
}
