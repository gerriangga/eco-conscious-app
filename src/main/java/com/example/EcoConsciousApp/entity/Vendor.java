package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private String vendorId;

    @NotEmpty(message = "Vendor name is required.")
    private String vendorName;

    @NotEmpty(message = "Vendor address is required.")
    private String vendorAddress;

    @Column(unique = true)
    @NotEmpty(message = "Vendor email is required.")
    private String vendorEmail;

    @NotEmpty(message = "Vendor sting is required.")
    private String password;

    @Column(name = "phone_number")
    @NotEmpty(message = "Vendor number is required.")
    private String vendorPhoneNumber;
    private Integer status;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("vendor")
    private List<VendorProductScrapsPurchase>purchases = new ArrayList<>();

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("vendor")
    private List<VendorUsableProductRegistration>registrations = new ArrayList<>();

    public Vendor(String vendorId, String vendorName, String vendorAddress, String vendorEmail, String password, String vendorPhoneNumber,
                  Integer status) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorEmail = vendorEmail;
        this.password = password;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.status = status;
    }

    public Vendor(String vendorName, String vendorAddress, String vendorEmail, String vendorPhoneNumber,
                  Integer status, String encode) {
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorEmail = vendorEmail;
        this.password = encode;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.status = status;
    }

}
