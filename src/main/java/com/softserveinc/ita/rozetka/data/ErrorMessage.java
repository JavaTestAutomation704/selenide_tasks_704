package com.softserveinc.ita.rozetka.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    INVALID_CURRENT_PASSWORD("Невірний поточний або тимчасовий пароль"),
    INVALID_NEW_PASSWORD("Пароль має бути не менше 6 символів, містити цифри та великі літери і не" +
            " повинен збігатися з ім'ям та ел.поштою"),
    ENTERED_NEW_PASSWORDS_DO_NOT_MATCH("Введені нові паролі не збігаються");

    @NonNull
    private final String messageUa;

}
