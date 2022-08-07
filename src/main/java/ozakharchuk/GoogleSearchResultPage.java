package ozakharchuk;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    private String resultLinkNamesXpath = "//div//a/h3";
    private String resultLinksXpath = "//div//a/h3/..";
    private String googleLogoXpath = "//div[@class='logo']/a";
    private String pageNumberXpath = "//div[@role='navigation']//td";
    private String previousPageXpath = "//a[@id='pnprev']";
    private String nextPageXpath = "//a[@id='pnnext']";

    public SelenideElement showResultLinkName(int numberOfLink){
       return  $$x( resultLinkNamesXpath ).get(numberOfLink);
    }

    public ElementsCollection showResultLink(){
        return  $$x(resultLinksXpath);
    }

    public GoogleHomePage openHomePage(){
        $x(googleLogoXpath).click();
        return new GoogleHomePage();
    }

    public SelenideElement showGoogleLogo(){
       return  $x(googleLogoXpath);
    }

    public GoogleSearchResultPage chooseNumberOfResultPage(int number){
         $$x(pageNumberXpath).get(number).click();
         return new GoogleSearchResultPage();
    }

    public GoogleSearchResultPage openNextResultPage(){
        $x(nextPageXpath).click();
        return new GoogleSearchResultPage();
    }

    public SelenideElement showNextPageLink(){
        return $x(nextPageXpath);
    }

    public GoogleSearchResultPage openPreviousResultPage(){
        $x(previousPageXpath).click();
        return new GoogleSearchResultPage();
    }

    public SelenideElement showPreviousPageLink(){
        return $x(previousPageXpath);
    }
}
