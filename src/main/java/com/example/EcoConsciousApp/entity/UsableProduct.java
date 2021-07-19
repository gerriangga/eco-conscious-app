package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_usable_product")
@Data
@NoArgsConstructor
public class UsableProduct {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "usable_product_id")
    private String id;
    private String usableProductName;
    private String usableProductDescription;
    private Date usableProductUploadDate;
    private Integer usableProductPrice;
    private Integer usableProductStock;
    private Boolean usableProductIsDeleted;

    @OneToMany(mappedBy = "usableProduct", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("usableProduct")
    private List<ProductScrapsVendor> usableProductVendorList = new ArrayList<>();
}
