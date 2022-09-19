package com.softserveinc.ita.rozetka.download_application.pages;

import com.codeborne.selenide.ClickOptions;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.switchToTab;
import static java.lang.String.format;

public abstract class DownloadApplicationsBasePage {

    protected DownloadApplicationsBasePage open(String pageName) {
        $x(format("//div[contains(@class, 'main-stores')]/a[contains(@href, '%s')]", pageName))
                .scrollIntoView(false)
                .click(ClickOptions.usingJavaScript());
        switchToTab(pageName);
        return this;
    }

    abstract protected boolean isOpened();
}
