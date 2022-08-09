package mvynokur;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private final String headerXpath = "//h3[@class = 'LC20lb MBeuO DKV0Md']";

    public int amountOfLinks() {
        return $$x(headerXpath).size();
    }

    public String getLinkNameNumber(int number) {
        return $x("//div[" + number + "]" + headerXpath + "/parent::a").text().toLowerCase();
    }

    public String getDescriptionTextByLinkNumber(int number) {
        return $$x("//div[@class = 'Uroaid']").get(number - 1).text().toLowerCase();
    }

    public String getLinkUrlNumber(int number) {
        return $x("//div[" + number + "]" + headerXpath + "/parent::a").getAttribute("href");
    }
}
