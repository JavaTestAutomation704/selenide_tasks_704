package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestRunner{

    @Test
    public void verifyRegistrationCapability() {
        boolean isRegistrationModalOpen = homePage
                .getHeader()
                .startRegistrationModal()
                .isOpen();

        assertTrue(isRegistrationModalOpen);
    }
}
