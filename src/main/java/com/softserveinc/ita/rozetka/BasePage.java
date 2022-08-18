package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;

public abstract class BasePage {
    public Header getHeader() {
        return new Header();
    }
}
