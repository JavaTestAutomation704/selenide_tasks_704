package ytarasovych;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends BaseMethods {

    private final String searchTerm = "funny dogs";
    private final String expectedTerm = "dog";
    private String textFromLink;

    @Test
    public void verifySearchFirstLink(){
        textFromLink = openGoogleHomePage
                .search(searchTerm)
                .getTextFromLink(1);
        Assert.assertTrue(textFromLink.contains(expectedTerm));
    }

    @Test
    public void verifySearchNinthLink(){
       boolean isUrlValid = openGoogleHomePage
                .search(searchTerm)
                .getTextHrefUrl(9)
                .matches("(ftp|http|https)://www\\..*");
        Assert.assertTrue(isUrlValid);
    }

    @Test
    public void verifyGooglePageIsOpen(){

       boolean isHomePageOpen = openGoogleHomePage
                .search(searchTerm)
                .goGoogleHomePageByLogo()
                .isGoogleHomePageOpen();
       Assert.assertTrue(isHomePageOpen);
    }

    @Test
    public void verifySearchFirstLinkInFifthPage(){
        textFromLink = openGoogleHomePage
                .search(searchTerm)
                .goToPage(5)
                .getTextFromLink(1);
        Assert.assertTrue(textFromLink.contains(expectedTerm));
    }

    @Test
    public void verifySearchNineLinksAreDisplayed(){
        int numberOfLinks = openGoogleHomePage
                .search(searchTerm)
                .getNumberOfLinks();
        Assert.assertTrue(numberOfLinks >= 9);
    }

    @Test
    public void verifySearchFirstLinkChangedInput(){
        textFromLink = openGoogleHomePage
                .inputSearchTerm(searchTerm)
                .clearSearchField()
                .search("funny kitten")
                .getTextFromLink(1);
        Assert.assertFalse(textFromLink.contains(expectedTerm));
    }

    @Test
    public void verifyLogoIsDisplayed(){
        boolean isGoogleLogoVisible = openGoogleHomePage
                .search(searchTerm)
                .isGoogleLogoVisible();
        Assert.assertTrue(isGoogleLogoVisible);
    }

    @Test
    public void verifyBtnNextPageIsDisplayed(){
        boolean isBtnNextPageVisible = openGoogleHomePage
                .search(searchTerm)
                .isBtnNextPageVisible();
        Assert.assertTrue(isBtnNextPageVisible);
    }

    @Test
    public void verifyBtnNextPageAndPreviousAreDisplayed(){
        GoogleSearchResultPage googleSearchPage = openGoogleHomePage
                .search(searchTerm);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(googleSearchPage
                .isBtnNextPageVisible());
        softAssert.assertTrue(googleSearchPage
                .goToPage(4)
                .isBtnPreviousPageVisible());
        softAssert.assertAll();
    }
}