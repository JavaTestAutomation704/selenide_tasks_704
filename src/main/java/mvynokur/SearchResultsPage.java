package mvynokur;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private final String LinkName = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public ElementsCollection searchResultLinksNames() {
        return $$x(LinkName);
    }

    public SelenideElement searchResultLink(int index) {
        return $x("//div[" + index + "]" + LinkName + "/parent::a");
    }

    public ElementsCollection searchResultsDescriptions() {
        return $$x("//div[@class = 'Uroaid']");
    }
}
