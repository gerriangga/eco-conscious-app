package com.example.EcoConsciousApp.entity;

import com.example.EcoConsciousApp.anotation.UniqueEmail;
import com.example.EcoConsciousApp.anotation.UniquePhoneNumber;
import com.example.EcoConsciousApp.constant.QuerySQLConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = QuerySQLConstant.QUERY_DELETE_CUSTOMER)
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private String id;

    @NotEmpty(message = ResponseMessage.FULL_NAME_REQUIRED)
    private String fullName;

    @NotEmpty(message = ResponseMessage.ADDRESS_REQUIRED)
    private String address;

    @Column(unique = true)
    @UniquePhoneNumber
    @NotEmpty(message = ResponseMessage.PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @Column(unique = true)
    @UniqueEmail
    @NotEmpty(message = ResponseMessage.EMAIL_REQUIRED)
    @Email(message = ResponseMessage.EMAIL_VALID_FORMAT)
    private String email;

    @NotEmpty(message = ResponseMessage.PASSWORD_REQUIRED)
    @Size(min = 8, message = ResponseMessage.PASSWORD_MIN_LENGTH)
    private String password;

    private String photo;
    @Lob
    private byte[] data;

    private Integer status = 0;

    private Boolean isDeleted = Boolean.FALSE;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("customer")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

}
