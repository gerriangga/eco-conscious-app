package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_vendor_productscraps_detail")
@Data
@NoArgsConstructor
public class VendorProductScrapsPurchaseDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_detail_id")
    private String id;
    private Double subtotal;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("purchaseDetails")
    private VendorProductScrapsPurchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("purchaseDetails")
    private ProductScraps productScraps;
}
