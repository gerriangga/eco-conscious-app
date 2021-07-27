package com.example.EcoConsciousApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendorSearchDTO {
    public String searchVendorName;
    public String searchVendorAddress;
    public Integer searchStatus;

    public VendorSearchDTO(String searchVendorName, String searchVendorAddress, Integer searchStatus ) {
        this.searchVendorName = searchVendorName;
        this.searchVendorAddress = searchVendorAddress;
        this.searchStatus = searchStatus;
    }

}
