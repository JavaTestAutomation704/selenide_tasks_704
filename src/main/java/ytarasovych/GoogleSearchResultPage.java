package ytarasovych;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleSearchResultPage {

    public GoogleHomePage goGoogleHomePageByLogo(){
        $x("//img[@alt='Google']").click();
        return new GoogleHomePage();
    }

    public GoogleSearchResultPage goToPage(int numberOfPage){
        $x("//a[@aria-label='Page " + numberOfPage + "']").click();
        return this;
    }

    public String getTextFromLink(int numberOfLink){
        return $x("(//div[@id='rso']//h3)[" + numberOfLink + "]")
                .getText()
                .toLowerCase();
    }

    public String getTextHrefUrl(int numberOfLink){
        return $x("(//div[@id='rso']//h3/ancestor::a)[" + numberOfLink + "]")
                .getAttribute("href");
    }

    public int getNumberOfLinks(){
        return $$x("//div[@id='rso']/div").size();
    }

    public boolean isGoogleLogoVisible(){
        return $x("//img[@alt='Google']")
                .isDisplayed();
    }

    public boolean isBtnNextPageVisible(){
        return $x("//a[@id='pnnext']")
                .isDisplayed();
    }

    public boolean isBtnPreviousPageVisible(){
        return $x("//a[@id='pnprev']")
                .isDisplayed();
    }
}