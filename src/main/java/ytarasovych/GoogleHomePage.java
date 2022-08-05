package ytarasovych;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    private final String inputField = "//input[@title]";

    public GoogleSearchResultPage search(String searchTerm){
        $x(inputField)
                .setValue(searchTerm)
                .pressEnter();
        return new GoogleSearchResultPage();
    }

    public GoogleHomePage inputSearchTerm(String searchTerm){
        $x(inputField)
                .setValue(searchTerm);
        return new GoogleHomePage();
    }

    public GoogleHomePage clearSearchField(){
        $x(inputField)
                .clear();
        return new GoogleHomePage();
    }

    public GoogleHomePage verifyGoogleHomePageIsOpen(){
        $x("//html")
                .shouldHave(attributeMatching("itemtype","http.+/WebPage"));
        $x("//img[@alt='Google']")
                .shouldHave(attribute("width","272"));
        $x("//div[@id='SIvCob']")
                .shouldBe(visible);
        return new GoogleHomePage();
    }
}