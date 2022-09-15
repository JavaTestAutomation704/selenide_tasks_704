package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Seller {
    private final String shopName;
    private final String siteUrl;
    private final String productsAmount;
    private final String fullName;
    private final String position;
    private final String email;
    private final String phoneNumber;
}