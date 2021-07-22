package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "mst_usable_product")
@SQLDelete(sql = "UPDATE mst_usable_product SET usable_product_is_deleted = true WHERE usable_product_id = ?")
@Data
@NoArgsConstructor
public class UsableProduct {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "usable_product_id")
    private String id;

    @NotEmpty(message = "Product name is required.")
    private String usableProductName;

    @NotEmpty(message = "Product description is required.")
    private String usableProductDescription;

    private Date usableProductUploadDate = new Date();

    @NotNull(message = "Product price is required.")
    @Positive(message = "Product price must be greater than 0")
    private Double usableProductPrice;

    @NotNull(message = "Product stock is required.")
    @Positive(message = "Product stock must be greater than 0")
    private Integer usableProductStock;

    private Boolean usableProductIsDeleted = Boolean.FALSE;
}
