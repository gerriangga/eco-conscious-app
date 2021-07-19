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
@Table(name = "mst_product_scraps")
@Data
@NoArgsConstructor
public class ProductScraps {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String id;
    private String productName;
    private String productDescription;
    private Date uploadDate;
    private Integer productPrice;
    private Integer stock;
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties("listProducts")
    private User user;

    private String userID;

    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties("productScrapsList")
    private Category category;

    @OneToMany(mappedBy = "productScraps", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("productScraps")
    private List<ProductScrapsVendor> productScrapsVendorList = new ArrayList<>();

}
