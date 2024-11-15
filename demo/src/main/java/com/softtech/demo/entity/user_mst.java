package com.softtech.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class user_mst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "TRAN_CD")
    private Integer tranCd;

    private String name;

    @Column(length = 6, name = "bankCode")
    private String bankCode;
    private String password;
    private String phone;
    private String email;
    private String address;
    private String city;
}
