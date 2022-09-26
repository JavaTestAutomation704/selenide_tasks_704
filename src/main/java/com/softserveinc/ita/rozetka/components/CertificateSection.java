package com.softserveinc.ita.rozetka.components;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

@RequiredArgsConstructor
public class CertificateSection {

    @NonNull
    private int orderNumber;

    public boolean isAddCertificateOptionAvailable() {
        return isVisible(format("(//div[@class = 'checkout-order'])[%d]//button[contains(@class, 'certificate')]", orderNumber));
    }
}