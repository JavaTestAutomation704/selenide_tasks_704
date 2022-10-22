package com.softserveinc.ita.rozetka.service_center;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;

public class ResultPage {

    public String getTitle() {
        return $x("//h1").text();
    }

    public int getServiceInfoSize() {
        return getCollectionSize("//ul[@class='service-info']");
    }
}