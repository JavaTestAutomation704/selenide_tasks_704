package rozetka;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class HeaderComponent {

    public HeaderComponent logInViaUserIcon() {
        $x("//rz-user").click();
        return this;
    }

    public boolean isLogInModalVisible() {
        return isVisible("//rz-user-identification");
    }
}