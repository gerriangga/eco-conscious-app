package com.example.EcoConsciousApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_customer")
@SQLDelete(sql = "UPDATE mst_customer SET is_deleted = true WHERE customer_id = ?")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "customer_id")
    private String id;

    @NotEmpty(message = "firstName is required.")
    private String firstName;

    @NotEmpty(message = "lastName is required.")
    private String lastName;

    @Column(name = "customer_address")
    @NotEmpty(message = "address is required.")
    private String address;

    @NotEmpty(message = "phoneNumber is required.")
    private String phoneNumber;

    @Column(name = "email_address")
    @NotEmpty(message = "email is required.")
    @Email(message = "Email should be a valid email format.")
    private String email;

    @NotEmpty(message = "password is required.")
    @Size(min = 8, message = "password should have at least 8 characters.")
    private String password;

    private Integer status = 0;
    private Date createdAt = new Date();
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("customer")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

}
