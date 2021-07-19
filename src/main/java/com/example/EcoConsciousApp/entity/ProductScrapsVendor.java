package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="trx_productscraps_vendor")
@Data
@NoArgsConstructor
public class ProductScrapsVendor {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "productscraps_vendor_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("productScrapsVendorList")
    private ProductScraps productScraps;

    @ManyToOne
    @JoinColumn(name="vendor_id")
    @JsonIgnoreProperties("productScrapsVendorList")
    private Vendor vendor;

}
