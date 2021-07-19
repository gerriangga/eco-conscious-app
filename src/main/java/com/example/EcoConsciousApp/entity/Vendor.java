package com.example.EcoConsciousApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
}
