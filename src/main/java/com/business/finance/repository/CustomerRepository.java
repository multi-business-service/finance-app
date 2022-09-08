package com.business.finance.repository;

import com.business.finance.model.customer.CustomerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerInfoEntity, String> {
}
