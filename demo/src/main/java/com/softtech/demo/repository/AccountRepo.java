package com.softtech.demo.repository;

import com.softtech.demo.entity.account_mst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<account_mst, Integer > {

    List<account_mst> findByUser_UserCd();
}
