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
@Table(name = "trx_purchase_product")
@Data
@NoArgsConstructor
public class PurchaseProduct {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_product_id")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseProductDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("purchaseProducts")
    private Customer customer;

    @OneToMany(mappedBy = "purchaseProduct")
    @JsonIgnoreProperties("purchaseProducts")
    private List<PurchaseProductDetail> purchaseProductDetails = new ArrayList<>();

}
