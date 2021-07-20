package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_product_detail")
@Data
@NoArgsConstructor
public class PurchaseProductDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_product_detail_id")
    private String id;

    private Double priceSell;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_product_id")
    @JsonIgnoreProperties("purchaseProductDetails")
    private PurchaseProduct purchaseProduct;

    @ManyToOne
    @JoinColumn(name = "usable_product_id")
    private UsableProduct usableProduct;

}
