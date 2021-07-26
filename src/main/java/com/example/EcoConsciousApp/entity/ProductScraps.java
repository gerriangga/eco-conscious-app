package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_product_scraps")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ProductScraps {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String id;

    @NotEmpty(message = "Product name is required") //not inputted or empty is not valid
    private String productName;

    private String productDescription;

    private Date uploadDate;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private Integer stock;

    private Boolean isDeleted;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "tbl_product_supplier",
                joinColumns = @JoinColumn(name = "product_scraps_id"),
                inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    //@JsonManagedReference
    private List<Supplier> suppliers;


}
