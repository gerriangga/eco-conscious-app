package com.example.EcoConsciousApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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


}
