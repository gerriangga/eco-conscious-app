package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "Please provide a first name")
    private String firstName;

    @NotEmpty(message = "Please provide a last name")
    private String lastName;

    @Column(name = "customer_address")
    @NotEmpty(message = "Please provide a address")
    private String address;

    @NotEmpty(message = "Please provide a phone number")
    private String phoneNumber;

    @NotEmpty(message = "Please provide a email")
    @Email(message = "Email not valid")
    private String email;

    @NotEmpty(message = "Please provide a password")
    private String password;

    @NotNull(message = "Please provide a status")
    private Integer status;

    private Boolean isDeleted;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("customer")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

}
