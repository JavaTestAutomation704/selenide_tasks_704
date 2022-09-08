package com.softserveinc.ita.rozetka.components;

import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@RequiredArgsConstructor
public class ContactInformation {

    private final String surnameXpath;
    private final String nameXpath;
    private final String phoneXpath;

    public String getName() {
        return $x(nameXpath).val();
    }

    public String getPhone() {
        return $x(phoneXpath).val();
    }

    public String getSurname() {
        return $x(surnameXpath).val();
    }
}