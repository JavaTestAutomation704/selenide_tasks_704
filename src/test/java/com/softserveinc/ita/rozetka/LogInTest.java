package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogInTest extends TestRunner {

    @Test
    public void verifyLoggingCapability() {
        boolean isLogInModalOpen = homePage
                .getHeader()
                .startLoggingIn()
                .isOpen();

        assertTrue(isLogInModalOpen);
    }
}
