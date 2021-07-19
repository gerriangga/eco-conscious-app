package com.example.EcoConsciousApp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_user")
@Data
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String id;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer status;
    @Column(name = "phone_number")
    private String phoneNumber;


}
