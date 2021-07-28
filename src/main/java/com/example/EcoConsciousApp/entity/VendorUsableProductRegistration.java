package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_vendor_usableproduct")
@Data
@NoArgsConstructor
public class VendorUsableProductRegistration {
    @Id
    @GeneratedValue
    @Column(name = "registration_id")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonIgnoreProperties("registrations")
    private Vendor vendor;

    @OneToMany(mappedBy = "registration")
    @JsonIgnoreProperties("registration")
    private List<VendorUsableProductRegistrationDetail> registrationDetails = new ArrayList<>();


}
