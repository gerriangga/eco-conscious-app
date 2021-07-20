package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_customer")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "customer_id")
    private String id;

    private String firstName;
    private String lastName;
    @Column(name = "customer_address")
    private String address;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer status;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

}
