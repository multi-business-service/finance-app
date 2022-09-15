package com.ak.finance.model;

import com.ak.finance.controller.constrants.AppEnumConstants;
import com.ak.finance.helper.SequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import org.hibernate.annotations.Parameter;
@Data
@Entity
@Table(
		schema = "customer",
		name = "member_info"
)
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer.member_seq")
	@GenericGenerator(
			name = "customer.member_seq",
			strategy = "com.ak.finance.helper.SequenceIdGenerator",
			parameters = {
					@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MEM"),
					@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
			}
	)
	private String cuid;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "adress")
	private String address;
	@Column(name = "member_role")
	@Enumerated(EnumType.STRING)
	private AppEnumConstants.MemberRole role;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_password")
	private String password;
	@Column(name = "mobile_no")
	private BigInteger mobileNo;
	@Column(name = "alternate_no")
	private BigInteger alternateNo;
	@Column(name = "aadaar_no")
	private BigInteger aadaarNo;
}
