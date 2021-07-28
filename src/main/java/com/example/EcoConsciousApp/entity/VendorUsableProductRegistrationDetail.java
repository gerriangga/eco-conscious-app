package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_vendor_usableproduct_detail")
@Data
@NoArgsConstructor
public class VendorUsableProductRegistrationDetail {
    @Id
    @GeneratedValue
    @Column(name = "registration_detail_id")
    private String id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    @JsonIgnoreProperties("registrationDetails")
    private VendorUsableProductRegistration registration;

    @ManyToOne
    @JoinColumn(name = "usable_product_id")
    @JsonIgnoreProperties("registrationDetails")
    private UsableProduct usableProduct;
}
