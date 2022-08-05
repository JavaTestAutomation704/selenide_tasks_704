package ytarasovych;

import org.testng.annotations.*;

public class GoogleTest extends BaseMethods {

    private final String searchTerm = "funny dogs";
    private final String expectedTerm = "dog";

    @Test
    public void verifySearchFirstLink(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyLinkContainsText(1, expectedTerm);
    }

    @Test
    public void verifySearchNinthLink(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyLinkContainsValidUrl(9);
    }

    @Test
    public void verifyGooglePageIsOpen(){
        new GoogleHomePage()
                .search(searchTerm)
                .goGoogleHomePageByLogo()
                .verifyGoogleHomePageIsOpen();
    }

    @Test
    public void verifySearchFirstLinkInFifthPage(){
        new GoogleHomePage()
                .search(searchTerm)
                .goToPage(5)
                .verifyLinkContainsText(1,expectedTerm);
    }

    @Test
    public void verifySearchNineLinksAreDisplayed(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyTotalNumberOfLinksIsGreaterThan(9);
    }

    @Test
    public void verifySearchFirstLinkAnotherInput(){
        new GoogleHomePage()
                .inputSearchTerm(searchTerm)
                .clearSearchField()
                .search("funny kitten")
                .verifyLinkDoesNotContainText(1,expectedTerm);
    }

    @Test
    public void verifyLogoIsDisplayed(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyLogoIsVisible();
    }

    @Test
    public void verifyBtnNextPageIsDisplayed(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyBtnNextPageIsVisible();
    }

    @Test
    public void verifyBtnNextPageAndPreviousAreDisplayed(){
        new GoogleHomePage()
                .search(searchTerm)
                .verifyBtnNextPageIsVisible()
                .goToPage(4)
                .verifyBtnPreviousPageIsVisible();
    }
}