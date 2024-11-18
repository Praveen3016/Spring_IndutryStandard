package com.softtech.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class account_mst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, name = "TRAN_CD")
    private Integer tranCd;

    // Establishing a foreign key relationship with user_mst
    @ManyToOne
    @JoinColumn(name = "USER_CD", nullable = false) // USER_CD is the foreign key column
    private user_mst user; // Mapping to user_mst entity

    private String accountType;

    private Number loanAmount; // Adjusted variable naming for standard conventions

    private Number totalPayedAm;

    private Number totalDueAm;

}
