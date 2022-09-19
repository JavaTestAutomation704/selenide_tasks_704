package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.CabinetSidebar;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProfilePage {

    private final CabinetSidebar cabinetSidebar = new CabinetSidebar();

    @Step("Profile page: sign out")
    public HomePage signOut() {
        $x("(//div/button[contains(@class,'button button--medium button--link')])[3]").click();
        return new HomePage();
    }

    public String getUserEmail() {
        return $x("//p[@class='cabinet-user__email']").text();
    }
}