package com.softserveinc.ita.rozetka.modals;

import static com.codeborne.selenide.Selenide.$;

public class CatalogModal {

    public String getCategoriesBlock() {
        return $("//ul[@class = 'menu-categories ng-star-inserted']").text();
    }
}