package com.business.finance.model.customer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
public class CustomerRole {

    @Id
    String role;
}
