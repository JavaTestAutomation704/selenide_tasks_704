package com.softserveinc.ita.rozetka.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CertificateData {
    private final String ownerPhone;
    private final String futureOwnerPhone;
    private final String code;
}