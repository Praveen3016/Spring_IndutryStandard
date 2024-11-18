package com.softtech.demo.repository;

import com.softtech.demo.entity.user_mst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<user_mst, Integer> {

    List<user_mst> findByBankCode(String bankCode);
    user_mst findByName(String name);
    user_mst findByUserCd(Integer userCd);
    @Transactional
    void  deleteByName(String name);

}
