package com.example.EcoConsciousApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "mst_category")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private String id;
    @Column(nullable = false)
    private String categoryName;
    private String categoryDescription;
    private Boolean isDeleted;
}
