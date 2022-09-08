package com.business.finance.model.customer;

import com.business.finance.constrants.AppEnumConstants;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(
		schema = "customer",
		name = "member_info"
)
public class CustomerInfoEntity {

	@Id
	private String cuid;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "adress")
	private String address;
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private AppEnumConstants.MemberRole role;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "mobile_no")
	private BigInteger mobileNo;
	@Column(name = "alternate_no")
	private BigInteger alternateNo;
	@Column(name = "aadaar_no")
	private BigInteger aadaarNo;
}
