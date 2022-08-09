package ytarasovych;

import com.codeborne.selenide.Selenide;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    private final String inputFieldXpath = "//input[@title]";

    public GoogleHomePage open(){
        Selenide.open("https://www.google.com");
        return this;
    }

    public GoogleHomePage inputSearchTerm(String searchTerm){
        $x(inputFieldXpath).setValue(searchTerm);
        return this;
    }

    public GoogleHomePage clearSearchField(){
        $x(inputFieldXpath).clear();
        return this;
    }

    public boolean isGoogleHomePageOpen(){
        return (isGoogleLogoWidthCorrect() && isLanguageSwitchingVisible());
    }

    public GoogleSearchResultPage search(String searchTerm){
        $x(inputFieldXpath)
                .setValue(searchTerm)
                .pressEnter();
        return new GoogleSearchResultPage();
    }

    private boolean isGoogleLogoWidthCorrect(){
        try {
            return Objects.equals($x("//img[@alt='Google']")
                    .getAttribute("width"), "272");
        }catch (NullPointerException e){
            return false;
        }
    }

    private boolean isLanguageSwitchingVisible(){
        return $x("//div[@id='SIvCob']")
                .isDisplayed();
    }
}