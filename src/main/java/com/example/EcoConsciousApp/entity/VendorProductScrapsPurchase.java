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
@Table(name = "trx_vendor_productscraps")
@Data
@NoArgsConstructor
public class VendorProductScrapsPurchase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_id")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonIgnoreProperties("purchases")
    private Vendor vendor;

    @OneToMany(mappedBy = "purchase")
    @JsonIgnoreProperties("purchase")
    private List<VendorProductScrapsPurchaseDetail> purchaseDetails = new ArrayList<>();
}
