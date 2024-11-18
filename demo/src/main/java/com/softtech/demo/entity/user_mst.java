package com.softtech.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class user_mst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "USER_CD")
    private Integer userCd;

    private String name;

    @Column(length = 6, name = "bankCode")
    private String bankCode;

    private String password;

    private String phone;

    private String email;

    private String address;

    private String city;

    // Optional: One-to-Many relationship to account_mst
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<account_mst> accounts;
}
