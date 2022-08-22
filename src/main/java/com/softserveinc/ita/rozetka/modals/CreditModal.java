package com.softserveinc.ita.rozetka.modals;

import static utils.WebElementUtil.isVisible;

public class CreditModal {

    public boolean isCreditModalVisible() {
        return isVisible("//credit-modal");
    }
}
