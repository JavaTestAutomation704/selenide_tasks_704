package odashynych;

import com.codeborne.selenide.CollectionCondition;
import odashynych.utils.WebElementUtil;

import java.time.Duration;

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

    public String getText(int linkNumber) {
        return $x(String.format("(//a/h3)[%s]", linkNumber)).text();
    }

    public String getUrl(int linkNumber) {
        return $x(String.format("(//a)[%s]", linkNumber)).getAttribute("href");
    }

    public boolean isGoogleLogoDisplayed() {
        return WebElementUtil.isDisplayed("//div[@class = 'logo']");
    }

    public GooglePage openHomePage() {
        $x("//div[@class = 'logo']").click();
        return new GooglePage();
    }

    public GoogleResultPage openPage(int number) {
        $x((String.format("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '%s')]", number))).click();
        return this;
    }

    public int getLinksAmount() {
        try {
            return $$x("(//a)").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1), Duration.ofSeconds(5)).size();
        } catch (AssertionError e) {
            return 0;
        }
    }

    public boolean isPreviousPageLinkDisplayed() {
        return WebElementUtil.isDisplayed("//a[@id='pnprev']");
    }

    public boolean isNextPageLinkDisplayed() {
        return WebElementUtil.isDisplayed("//a[@id='pnnext']");
    }
}
