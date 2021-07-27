package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.*;

@Entity
@Table(name = "mst_product_scraps")
@Data
@SQLDelete(sql = "UPDATE mst_usable_product SET usable_product_is_deleted = true WHERE usable_product_id = ?")
@NoArgsConstructor
public class ProductScraps {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String id;

    @NotEmpty(message = "Product name is required.")
    private String productName;
    private String productDescription;

    @NotNull(message = "Product price is required.")
    @Positive(message = "Product price must be greater than 0")
    private Integer productPrice;

    @NotNull(message = "Product stock is required.")
    @Positive(message = "Product stock must be greater than 0")
    private Integer stock;

    private String productScrapsImage;

    @Lob
    private byte[] data;

    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties("listProducts")
    private User user;

    @OneToMany(mappedBy = "productScraps", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("productScraps")
    private List<VendorProductScrapsPurchaseDetail> purchaseDetails = new ArrayList<>();


}
