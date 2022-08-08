package mvynokur;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private final String linkName = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public ElementsCollection getLinkNames() {
        return $$x(linkName);
    }

    public SelenideElement getLinkNameByNumber(int index) {
        return $x("//div[" + index + "]" + linkName + "/parent::a");
    }

    public ElementsCollection getDescriptions() {
        return $$x("//div[@class = 'Uroaid']");
    }
}
