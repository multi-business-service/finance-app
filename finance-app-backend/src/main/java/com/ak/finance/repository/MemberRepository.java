package com.ak.finance.repository;

import com.ak.finance.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    MemberEntity findByMobileNo(BigInteger mobileNo);
}
