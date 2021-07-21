package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotEmpty(message = "Please provide a product name")
    private String usableProductName;

    @NotEmpty(message = "Please provide a product description")
    private String usableProductDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date usableProductUploadDate;

    @NotNull(message = "Please provide a product price")
    private Double usableProductPrice;

    @NotNull(message = "Please provide a product stock")
    private Integer usableProductStock;

    private Boolean usableProductIsDeleted;
}
