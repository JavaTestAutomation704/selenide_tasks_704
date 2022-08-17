package rozetka;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderComponent {

    public HeaderComponent logInViaUserIcon() {
        $x("//rz-user").click();
        return this;
    }
}