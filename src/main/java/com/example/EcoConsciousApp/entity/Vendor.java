package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_vendor")
@Data
@NoArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "vendor_id")
    private String id;
    private String vendorName;
    private String vendorAddress;
    @Column(unique = true)
    private String vendorEmail;
    private String password;
    @Column(name = "phone_number")
    private String vendorPhoneNumber;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("vendor")
    private List<ProductScrapsVendor> productScrapsVendorList = new ArrayList<>();

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("vendor")
    private List<UsableProductVendor> usableProductVendorList = new ArrayList<>();
}
