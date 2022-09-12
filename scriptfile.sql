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
