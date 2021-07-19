package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="trx_usableproduct_vendor")
@Data
@NoArgsConstructor
public class UsableProductVendor {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "usableproduct_vendor_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="usable_product_id")
    @JsonIgnoreProperties("usableProductVendorList")
    private UsableProduct usableProduct;

    @ManyToOne
    @JoinColumn(name="vendor_id")
    @JsonIgnoreProperties("usableProductVendorList")
    private Vendor vendor;
}
