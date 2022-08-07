package ykireyeva;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    GoogleSearchPage searchPage;
    GoogleSearchResultsPage resultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openGoogleSearchPageBeforeMethod() {
        Selenide.open("https://www.google.com/");
        setPages();
        baseSearch();
    }

    public void setPages() {
        searchPage = new GoogleSearchPage();
        resultsPage = new GoogleSearchResultsPage();
    }

    public void baseSearch() {
        searchPage.search("funny dogs");
    }
}
