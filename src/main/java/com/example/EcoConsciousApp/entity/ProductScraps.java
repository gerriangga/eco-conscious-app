package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.*;


@Entity
@Table(name = "mst_product_scraps")
@Data
@SQLDelete(sql = "UPDATE mst_usable_product SET usable_product_is_deleted = true WHERE usable_product_id = ?")
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ProductScraps {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String id;


    @NotEmpty(message = "Product name is required.")
    private String productName;

    private String productDescription;

    private Date uploadDate;

    @NotNull(message = "Product price is required.")
    @Positive(message = "Product price must be greater than 0")
    private Integer productPrice;

    @NotNull(message = "Product stock is required.")
    @Positive(message = "Product stock must be greater than 0")
    private Integer stock;

    private Boolean isDeleted = Boolean.FALSE;

    private String productScrapsImage;

    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "productScraps", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("productScraps")
    private List<VendorProductScrapsPurchaseDetail> purchaseDetails = new ArrayList<>();

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "tbl_product_supplier",
                joinColumns = @JoinColumn(name = "product_scraps_id"),
                inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    //@JsonManagedReference
    private List<Supplier> suppliers;


}
