package ozakharchuk;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    private String searchFieldXpath = "//input[@name='q']";

    public GoogleHomePage() {
    }

    public GoogleSearchResultPage searchForText(String text){
        $x(searchFieldXpath).clear();
        $x(searchFieldXpath).setValue(text).pressEnter();
        return new GoogleSearchResultPage();
    }


}
