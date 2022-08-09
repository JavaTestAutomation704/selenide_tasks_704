package odashynych;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;


public class GoogleResultPage {

    public GoogleResultPage clearInputField() {
        $x("//input[@name='q']").clear();
        return this;
    }

    public GoogleResultPage search(String searchItem) {
        $x("//input[@name='q']").setValue(searchItem).pressEnter();
        return this;
    }

    public String getTextFromLinkNumber(int linkNumber) {
        return $x(String.format("(//a/h3)[%s]", linkNumber)).text();
    }

    private String getLinkElementXpath(int linkNumber) {
        return String.format("(//a)[%s]", linkNumber);
    }

    public String getLinkNumberHref(int linkNumber) {
        return $x(getLinkElementXpath(linkNumber)).getAttribute("href");
    }

    public void openLink(int linkNumber) {
        $x(getLinkElementXpath(linkNumber)).click();
    }

    public String getLogoXpath() {
        return "//div[@class = 'logo']";
    }

    public GoogleResultPage openGoogleLogo() {
        $x(getLogoXpath()).click();
        return this;
    }

    public GoogleResultPage goToPage(int page) {
        $x((String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%s')]", page)))
                .click();
        return this;

    }

    public int getNumberOfAllLinks(){
        return $$x("(//a)").size();
    }

    public String getPreviousPageXpath() {
        return "//a[@id='pnprev']";
    }

    public String getNextPageXpath() {
        return "//a[@id='pnnext']";
    }
}
