package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeliveryAddress {
    private final String city;
    private final String street;
    private final String building;
    private final String flat;
}
