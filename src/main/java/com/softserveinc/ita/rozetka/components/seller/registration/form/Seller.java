package com.softserveinc.ita.rozetka.components.seller.registration.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Seller {
    @NonNull
    private String shopName;
    @NonNull
    private String siteUrl;
    @NonNull
    private String productsAmount;
    @NonNull
    private String fullName;
    @NonNull
    private String position;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
}