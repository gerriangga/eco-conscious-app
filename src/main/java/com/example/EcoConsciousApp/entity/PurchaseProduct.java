package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_purchase_product")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class PurchaseProduct {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_product_id")
    private String id;

    @CreatedDate
    private Date purchaseProductDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("purchaseProducts")
    private Customer customer;

    @OneToMany(mappedBy = "purchaseProduct")
    @JsonIgnoreProperties("purchaseProducts")
    private List<PurchaseProductDetail> purchaseProductDetails = new ArrayList<>();

}

