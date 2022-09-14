package com.ak.finance.repository;

import com.ak.finance.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    @Transactional
    MemberEntity findByMobileNo(BigInteger mobileNo);
    @Transactional
    List<MemberEntity> removeByMobileNo(BigInteger mobileNo);
}
