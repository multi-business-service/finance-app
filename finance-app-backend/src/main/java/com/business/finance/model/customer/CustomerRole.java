package com.business.finance.model.customer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(
        schema = "customer",
        name = "member_role"
)
public class CustomerRole {

    @Id
    @Column(name="member_role")
    String memberRole;
}
