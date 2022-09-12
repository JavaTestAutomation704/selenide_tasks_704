package com.softserveinc.ita.rozetka.utils;

import org.testng.ITestResult;
import org.testng.internal.ExitCodeListener;

public class AllureScreenShooter extends ExitCodeListener {
    public void onTestFailure(final ITestResult result) {
        super.onTestFailure(result);
        AllureHelpers.takeScreenshot();
    }
}