package com.xtramile.patientcrud.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String addressLine;
    private String suburb;
    private String state;
    private String postcode;
}
