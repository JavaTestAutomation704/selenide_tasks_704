package olenka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;


public class GoogleResultPage extends GoogleBasePage {
    @Override
    public GoogleResultPage clearInputField() {
        $x(inputField).clear();
        return this;
    }

    public GoogleResultPage linkContainsText(int link, String word) {
        $x(String.format("(//a/h3)[%s]", link)).shouldHave(text(word));
        return this;
    }

    public GoogleResultPage linkNotContainsText(int link, String word) {
        $x(String.format("(//a/h3)[%s]", link)).shouldNotHave(text(word));
        return this;
    }

    private SelenideElement getLinkElement(int link) {
        return $x((String.format("(//a)[%s]", link)));
    }

    public String getLinkHref(int link) {
        return getLinkElement(link).getAttribute("href");
    }

    public void openLink(int link) {
        getLinkElement(link).click();
    }

    public SelenideElement getLogo() {
        return $x("//div[@class = 'logo']");
    }

    public GoogleResultPage clickOnLogo() {
        getLogo().click();
        return this;
    }

    public GoogleResultPage goToPage(int page) {
        $x((String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%s')]", page)))
                .click();
        return new GoogleResultPage();

    }

    public ElementsCollection getAllLinks() {
        return $$x("(//a)");
    }

    public SelenideElement getPreviousPage() {
        return $x("//a[@id='pnprev']");
    }

    public SelenideElement getNextPage() {
        return $x("//a[@id='pnnext']");
    }
}
