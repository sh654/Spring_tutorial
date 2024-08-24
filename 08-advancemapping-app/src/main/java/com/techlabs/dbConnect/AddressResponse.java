package com.techlabs.dbConnect;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int addressId;
    private String buildingName;
    private String cityName;
    private int pinCode;
}

