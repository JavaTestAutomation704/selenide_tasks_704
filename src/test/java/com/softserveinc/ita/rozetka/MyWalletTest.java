package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.profile.ProfilePage;

import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.testng.annotations.Test;

public class MyWalletTest extends LogInViaFacebookTestRunner {
    @Test
    public void verifyThatUserCanNotAddCardWithInvalidData() {
        /*var cardDataSection = homePage
                .getHeader()
                .openMainSidebar()
                .openProfilePage()
                .openCardDataSection();
    }*/
}
