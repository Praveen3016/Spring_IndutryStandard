package com.softtech.demo.repository;

import com.softtech.demo.entity.user_mst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepo extends JpaRepository<user_mst, Integer> {

    List<user_mst> findByBankCode(String bankCode);

}
