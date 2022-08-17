package deprecated.odashynych;

import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleResultPage {

    private final String inputFieldXpath = "//input[@name='q']";

    public GoogleResultPage clearInputField() {
        $x(inputFieldXpath).clear();
        return this;
    }

    public GoogleResultPage search(String searchItem) {
        $x(inputFieldXpath).setValue(searchItem).pressEnter();
        return this;
    }

    public String getText(int linkNumber) {
        return $x(String.format("(//a/h3)[%s]", linkNumber)).text();
    }

    public String getUrl(int linkNumber) {
        return $x(String.format("(//a)[%s]", linkNumber)).getAttribute("href");
    }

    public boolean isGoogleLogoVisible() {
        return WebElementUtil.isVisible("//div[@class = 'logo']");
    }

    public GooglePage openGooglePageViaLogo() {
        $x("//div[@class = 'logo']").click();
        return new GooglePage();
    }

    public GoogleResultPage openSearchPage(int number) {
        $x((String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%s')]", number))).click();
        return this;
    }

    public int getLinksAmount() {
        return WebElementUtil.getCollectionSize("//a");
    }

    public boolean isPreviousPageLinkVisible() {
        return WebElementUtil.isVisible("//a[@id='pnprev']");
    }

    public boolean isNextPageLinkVisible() {
        return WebElementUtil.isVisible("//a[@id='pnnext']");
    }
}
