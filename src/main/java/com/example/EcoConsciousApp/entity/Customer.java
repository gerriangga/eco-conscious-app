package com.example.EcoConsciousApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(unique = true)
    private String email;
    private String password;
    private Integer status;
    private String phoneNumber;
}
