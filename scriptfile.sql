-- DROP SCHEMA balancesheet;

CREATE SCHEMA balancesheet AUTHORIZATION postgres;
CREATE SCHEMA customer AUTHORIZATION postgres;
CREATE SCHEMA business AUTHORIZATION postgres;
CREATE SCHEMA common AUTHORIZATION postgres;


-- customer.member_info definition

-- Drop table

-- DROP TABLE customer.member_info;

CREATE TABLE customer.member_info (
	cuid varchar(255) NOT NULL,
	aadaar_no numeric(19, 2) NOT NULL,
	adress varchar(255) NOT NULL,
	alternate_no numeric(19, 2) NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NULL,
	mobile_no numeric(19, 2) NOT NULL,
	user_password varchar(255) NULL,
	member_role varchar(255) NOT NULL,
	user_name varchar(255) NULL,
	CONSTRAINT aadaar_no_unq UNIQUE (aadaar_no),
	CONSTRAINT member_info_pkey PRIMARY KEY (cuid),
	CONSTRAINT mobile_unq UNIQUE (mobile_no)
);


-- customer.member_info foreign keys

ALTER TABLE customer.member_info ADD CONSTRAINT member_role_fk FOREIGN KEY (member_role) REFERENCES customer.member_role(member_role);

-- customer.member_role definition

-- Drop table

-- DROP TABLE customer.member_role;

CREATE TABLE customer.member_role (
	member_role varchar(255) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (member_role)
);

-- customer.member_seq definition

-- DROP SEQUENCE customer.member_seq;

CREATE SEQUENCE customer.member_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- balancesheet.investment_details definition

-- Drop table

-- DROP TABLE balancesheet.investment_details;

CREATE TABLE balancesheet.investment_details (
	invester_id varchar(15) NOT NULL,
	invested_amt numeric(10) NOT NULL,
	invested_in varchar(10) NOT NULL,
	invested_date date NOT NULL,
	liablity_id varchar(15) NULL,
	update_by varchar(15) NULL,
	update_time timetz NULL,
	CONSTRAINT investment_details_pkey PRIMARY KEY (invested_amt, invested_in, invested_date)
);


-- balancesheet.investment_details foreign keys

ALTER TABLE balancesheet.investment_details ADD CONSTRAINT invested_in_fk FOREIGN KEY (invested_in) REFERENCES common.business_info(business_type);

-- balancesheet.liablility_info definition

-- Drop table

-- DROP TABLE balancesheet.liablility_info;

CREATE TABLE balancesheet.liablility_info (
	liablity_id varchar(15) NOT NULL,
	borrowed_from varchar NOT NULL,
	total_liable_amt numeric NOT NULL,
	pending_liable numeric NULL,
	liable_type varchar(15) NULL,
	interest_rate numeric(3) NULL,
	total_tenure int4 NULL,
	updated_by varchar NULL,
	updated_time timetz NULL,
	paid_amt numeric(10) NULL,
	CONSTRAINT liablility_info_pkey PRIMARY KEY (liablity_id)
);



-- balancesheet.sharing_details definition

-- Drop table

-- DROP TABLE balancesheet.sharing_details;

CREATE TABLE balancesheet.sharing_details (
	user_id varchar(15) NOT NULL,
	share_split_type varchar(15) NOT NULL,
	share_type varchar(10) NOT NULL,
	"share" numeric(10) NOT NULL,
	share_for varchar(10) NOT NULL,
	share_start_date date NOT NULL,
	share_end_date date NULL
);


-- balancesheet.sharing_details foreign keys

ALTER TABLE balancesheet.sharing_details ADD CONSTRAINT share_for_fk FOREIGN KEY (share_for) REFERENCES common.business_info(business_type);
ALTER TABLE balancesheet.sharing_details ADD CONSTRAINT share_split_fk FOREIGN KEY (share_split_type) REFERENCES common.share_split_type(share_split_type);
ALTER TABLE balancesheet.sharing_details ADD CONSTRAINT share_type_fk FOREIGN KEY (share_type) REFERENCES common.share_type(share_type);


-- business.loan_details definition

-- Drop table

-- DROP TABLE business.loan_details;

CREATE TABLE business.loan_details (
	loan_id varchar(15) NOT NULL,
	business_type varchar(15) NULL,
	loan_amt numeric(10) NOT NULL,
	interest_rate int4 NOT NULL,
	loan_tenure int4 NULL,
	loan_given_date date NOT NULL,
	loan_start_date date NOT NULL,
	loan_payment_mode varchar NOT NULL,
	cuid varchar NOT NULL,
	emi_amt numeric NOT NULL,
	loan_status varchar NOT NULL,
	emi_repay_freq int4 NULL,
	pending_principal_amt numeric NULL,
	paid_emi_count int4 NULL,
	expected_colse_date date NULL,
	updated_by varchar NOT NULL,
	updated_time timetz NOT NULL,
	CONSTRAINT finance_details_pk PRIMARY KEY (loan_id)
);


-- business.loan_details foreign keys

ALTER TABLE business.loan_details ADD CONSTRAINT business_type_fk FOREIGN KEY (business_type) REFERENCES common.business_info(business_type);
ALTER TABLE business.loan_details ADD CONSTRAINT loam_payement_mode_fk FOREIGN KEY (loan_payment_mode) REFERENCES common.loan_payment_mode_info(loan_payment_mode);
ALTER TABLE business.loan_details ADD CONSTRAINT loan_status_fk FOREIGN KEY (loan_status) REFERENCES common.loan_status_info(loan_status);


-- business.loan_transaction definition

-- Drop table

-- DROP TABLE business.loan_transaction;

CREATE TABLE business.loan_transaction (
	loan_id varchar(10) NOT NULL,
	emi_amt numeric(10) NOT NULL,
	interest_amt numeric(10) NOT NULL,
	principal_amt numeric(10) NOT NULL,
	balance numeric(10) NOT NULL,
	interest_percent numeric(3) NOT NULL,
	principal_percent numeric(3) NOT NULL,
	updated_by varchar(10) NOT NULL,
	updated_time timetz NOT NULL
);


-- business.loan_transaction foreign keys

ALTER TABLE business.loan_transaction ADD CONSTRAINT loan_id_fk FOREIGN KEY (loan_id) REFERENCES business.loan_details(loan_id);


-- business.youtube definition

-- Drop table

-- DROP TABLE business.youtube;

CREATE TABLE business.youtube (
	channel_id varchar(15) NOT NULL,
	channel_name varchar(40) NULL,
	channel_category varchar(50) NULL,
	CONSTRAINT youtube_pkey PRIMARY KEY (channel_id)
);


-- common.business_info definition

-- Drop table

-- DROP TABLE common.business_info;

CREATE TABLE common.business_info (
	business_type varchar(8) NOT NULL,
	business_desc varchar(100) NULL,
	CONSTRAINT business_type_pk PRIMARY KEY (business_type),
	CONSTRAINT business_type_uq UNIQUE (business_type)
);



-- common.loan_lend_option_info definition

-- Drop table

-- DROP TABLE common.loan_lend_option_info;

CREATE TABLE common.loan_lend_option_info (
	loan_lend_option varchar(15) NOT NULL,
	CONSTRAINT loan_lend_option_info_pkey PRIMARY KEY (loan_lend_option)
);


-- common.loan_payment_mode_info definition

-- Drop table

-- DROP TABLE common.loan_payment_mode_info;

CREATE TABLE common.loan_payment_mode_info (
	loan_payment_mode varchar(8) NOT NULL,
	CONSTRAINT loan_period_pk PRIMARY KEY (loan_payment_mode)
);


-- common.loan_status_info definition

-- Drop table

-- DROP TABLE common.loan_status_info;

CREATE TABLE common.loan_status_info (
	loan_status_desc varchar(100) NULL,
	loan_status varchar(10) NOT NULL,
	CONSTRAINT loan_info_pk PRIMARY KEY (loan_status),
	CONSTRAINT loan_status UNIQUE (loan_status)
);


-- common.loan_type_info definition

-- Drop table

-- DROP TABLE common.loan_type_info;

CREATE TABLE common.loan_type_info (
	loan_type varchar(15) NOT NULL,
	CONSTRAINT loan_type_info_pkey PRIMARY KEY (loan_type)
);



-- common.share_split_type definition

-- Drop table

-- DROP TABLE common.share_split_type;

CREATE TABLE common.share_split_type (
	share_split_type varchar(15) NOT NULL,
	CONSTRAINT share_split_type_pkey PRIMARY KEY (share_split_type)
);


-- common.share_type definition

-- Drop table

-- DROP TABLE common.share_type;

CREATE TABLE common.share_type (
	share_type varchar(8) NOT NULL,
	CONSTRAINT share_type_pkey PRIMARY KEY (share_type)
);