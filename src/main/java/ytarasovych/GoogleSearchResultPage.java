package ytarasovych;

import com.codeborne.selenide.CollectionCondition;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    private final String resultLink = "//div[@id='rso']//h3";

    public GoogleHomePage goGoogleHomePageByLogo(){
        $x("//img[@alt='Google']")
                .click();
        return new GoogleHomePage();
    }

    public GoogleSearchResultPage goToPage(int numberOfPage){
        $x("//a[@aria-label='Page " + numberOfPage + "']")
                .click();
        return this;
    }

    public GoogleSearchResultPage verifyLinkContainsText(int numberOfLink, String text){
        $x("(" + resultLink + ")[" + numberOfLink + "]")
                .shouldHave(text(text));
        return this;
    }

    public GoogleSearchResultPage verifyLinkDoesNotContainText(int numberOfLink, String text){
        $x("(" + resultLink + ")[" + numberOfLink + "]")
                .shouldNot(text(text));
        return this;
    }

    public GoogleSearchResultPage verifyLinkContainsValidUrl(int numberOfLink){
        $x("(//div[@id='rso']//h3/ancestor::a)[" + numberOfLink + "]")
                .shouldHave(attributeMatching("href","https://www\\..+"));
        return this;
    }

    public GoogleSearchResultPage verifyTotalNumberOfLinksIsGreaterThan(int totalNumberOfLinks){
        $$x("//div[@id='rso']/div")
                .shouldBe(CollectionCondition.sizeGreaterThan(totalNumberOfLinks));
        return this;
    }

    public GoogleSearchResultPage verifyLogoIsVisible(){
        $x("//img[@alt='Google']")
                .shouldBe(visible);
        return this;
    }

    public GoogleSearchResultPage verifyBtnNextPageIsVisible(){
        $x("//a[@id='pnnext']/span[@class]")
                .shouldBe(visible);
        return this;
    }

    public GoogleSearchResultPage verifyBtnPreviousPageIsVisible(){
        $x("//a[@id='pnprev']")
                .shouldBe(visible);
        return this;
    }
}