package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.softserveinc.ita.rozetka.data.Language.UA;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {

    ROZETKA_PICK_UP("Самовивіз з наших магазинів", "Самовывоз из наших магазинов"),
    MEEST_PICK_UP("Самовивіз з Meest", "Самовывоз из Meest"),
    NOVA_POSHTA_PICK_UP("Самовивіз з Нової Пошти", "Самовывоз из Новой Почты");

    @NonNull
    private final String deliveryNameUa;
    @NonNull
    private final String deliveryNameRu;

    public String getDeliveryName(Language language) {
        switch (language) {
            case RU:
                return getDeliveryNameRu();
            case UA:
            default:
                return getDeliveryNameUa();
        }
    }
}