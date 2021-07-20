package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String usableProductName;
    private String usableProductDescription;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date usableProductUploadDate;
    private Double usableProductPrice;
    private Integer usableProductStock;
    private Boolean usableProductIsDeleted;
}
