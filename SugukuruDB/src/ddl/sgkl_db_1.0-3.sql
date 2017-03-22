SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS earnings_detail_table;
DROP TABLE IF EXISTS estimates_detail_table;
DROP TABLE IF EXISTS inventories_master;
DROP TABLE IF EXISTS order_details_table;
DROP TABLE IF EXISTS product_unit_table;
DROP TABLE IF EXISTS lending_detail_table;
DROP TABLE IF EXISTS sample_master;
DROP TABLE IF EXISTS stock_table;
DROP TABLE IF EXISTS puroduct_master;
DROP TABLE IF EXISTS category_master;
DROP TABLE IF EXISTS constants_master;
DROP TABLE IF EXISTS lone_table;
DROP TABLE IF EXISTS corporation_customer_master;
DROP TABLE IF EXISTS earnings_table;
DROP TABLE IF EXISTS delivering_table;
DROP TABLE IF EXISTS shipment_table;
DROP TABLE IF EXISTS order_table;
DROP TABLE IF EXISTS estimates_table;
DROP TABLE IF EXISTS personal_customer_master;
DROP TABLE IF EXISTS payment_table;
DROP TABLE IF EXISTS reminder_table;
DROP TABLE IF EXISTS settlement_table;
DROP TABLE IF EXISTS customers_distinction_master;
DROP TABLE IF EXISTS employment_master;
DROP TABLE IF EXISTS department_master;
DROP TABLE IF EXISTS maker_master;
DROP TABLE IF EXISTS messages_master;
DROP TABLE IF EXISTS rack_master;
DROP TABLE IF EXISTS suppliers_master;
DROP TABLE IF EXISTS unit_master;




/* Create Tables */

CREATE TABLE category_master
(
	category_id int(2) NOT NULL AUTO_INCREMENT,
	category_name varchar(10) NOT NULL,
	PRIMARY KEY (category_id),
	UNIQUE (category_id)
);


CREATE TABLE constants_master
(
	constant_id char(4) NOT NULL,
	value text NOT NULL,
	pg_name text,
	PRIMARY KEY (constant_id),
	UNIQUE (constant_id)
);


CREATE TABLE corporation_customer_master
(
	customer_id char(6) NOT NULL,
	generation int(2) NOT NULL,
	corporation_name varchar(50) NOT NULL,
	customer_name varchar(50) NOT NULL,
	customer_name_kana varchar(50) NOT NULL,
	abbreviation_name varchar(20) NOT NULL,
	office_name varchar(20) NOT NULL,
	postal_code char(8) NOT NULL,
	address text NOT NULL,
	tel varchar(12) NOT NULL,
	note text,
	cut_off_date date NOT NULL,
	recall_manner int(1) NOT NULL,
	delivery_address text NOT NULL,
	business_chage_preace varchar(20) NOT NULL,
	credit_limit int(12) NOT NULL,
	recode_date date NOT NULL,
	start_date date NOT NULL,
	expiry_date date,
	PRIMARY KEY (customer_id, generation),
	UNIQUE (customer_id)
);


CREATE TABLE customers_distinction_master
(
	customer_id char(6) NOT NULL,
	distinction_corporation int(1) DEFAULT 0 NOT NULL,
	distinction_personal int(1) DEFAULT 0 NOT NULL,
	mail_address varchar(50) NOT NULL,
	password varchar(20) NOT NULL,
	PRIMARY KEY (customer_id),
	UNIQUE (customer_id)
);


CREATE TABLE delivering_table
(
	order_id int(12) NOT NULL,
	maked_date date NOT NULL,
	delivered_date date NOT NULL,
	PRIMARY KEY (order_id),
	UNIQUE (order_id)
);


CREATE TABLE department_master
(
	department_id int(2) NOT NULL,
	department_name varchar(20) NOT NULL,
	PRIMARY KEY (department_id)
);


CREATE TABLE earnings_detail_table
(
	num int(4) NOT NULL AUTO_INCREMENT,
	earning_id int(12) NOT NULL,
	puroduct_id char(7) NOT NULL,
	puroduct_name varchar(50) NOT NULL,
	price int(8) NOT NULL,
	tax_fee int(8) NOT NULL,
	sold_amount int(4) NOT NULL,
	PRIMARY KEY (num, earning_id),
	UNIQUE (earning_id),
	UNIQUE (puroduct_id)
);


CREATE TABLE earnings_table
(
	earning_id int(12) NOT NULL AUTO_INCREMENT,
	order_id int(12) NOT NULL,
	customer_id char(6) NOT NULL,
	employment_id int(4) NOT NULL,
	shipment_date date NOT NULL,
	no_tax_total_fee int(10) NOT NULL,
	tax_fee int(8) NOT NULL,
	note text,
	PRIMARY KEY (earning_id),
	UNIQUE (earning_id),
	UNIQUE (order_id),
	UNIQUE (customer_id),
	UNIQUE (employment_id)
);


CREATE TABLE employment_master
(
	employment_id int(4) NOT NULL AUTO_INCREMENT,
	login_id varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	famiry_name varchar(10) NOT NULL,
	first_name varchar(10) NOT NULL,
	famiry_name_kana varchar(15) NOT NULL,
	first_name_kana varchar(15) NOT NULL,
	department_id int(2) NOT NULL,
	postal_code varchar(7) NOT NULL,
	prefecture varchar(5) NOT NULL,
	address text NOT NULL,
	hire_date date NOT NULL,
	leaving_date date,
	note text,
	PRIMARY KEY (employment_id),
	UNIQUE (employment_id),
	UNIQUE (login_id)
);


CREATE TABLE estimates_detail_table
(
	number_id int(3) NOT NULL AUTO_INCREMENT,
	estimates_id int(12) NOT NULL,
	puroduct_id char(7) NOT NULL,
	product_id varchar(50) NOT NULL,
	price int(8) NOT NULL,
	amount int(3) NOT NULL,
	no_tax_subtotal_fee int(10) NOT NULL,
	tax int(8) NOT NULL,
	subtotal_fee int(12) NOT NULL,
	stock_id int(12) NOT NULL,
	PRIMARY KEY (number_id, estimates_id),
	UNIQUE (estimates_id),
	UNIQUE (puroduct_id),
	UNIQUE (stock_id)
);


CREATE TABLE estimates_table
(
	estimates_id int(12) NOT NULL AUTO_INCREMENT,
	customer_id char(6) NOT NULL,
	delivery_date date NOT NULL,
	recovery_process int(1) NOT NULL,
	expiration_date date NOT NULL,
	note text,
	employment_id int(4) NOT NULL,
	create_date date NOT NULL,
	postage int(5) NOT NULL,
	no_tax_total_fee int(10) NOT NULL,
	tax_fee int(5) NOT NULL,
	total_fee int(12) NOT NULL,
	PRIMARY KEY (estimates_id),
	UNIQUE (estimates_id),
	UNIQUE (customer_id),
	UNIQUE (employment_id)
);


CREATE TABLE inventories_master
(
	inventories_id int(12) NOT NULL AUTO_INCREMENT,
	puroduct_id char(7) NOT NULL,
	puroduct_name varchar(50) NOT NULL,
	inventorie_amount int(5) NOT NULL,
	finished_amount int(5) NOT NULL,
	note text,
	PRIMARY KEY (inventories_id),
	UNIQUE (inventories_id),
	UNIQUE (puroduct_id)
);


CREATE TABLE lending_detail_table
(
	lone_id int(12) NOT NULL,
	sample_id char(10) NOT NULL,
	lending_amount int(3) NOT NULL,
	PRIMARY KEY (lone_id, sample_id),
	UNIQUE (lone_id),
	UNIQUE (sample_id)
);


CREATE TABLE lone_table
(
	lone_id int(12) NOT NULL AUTO_INCREMENT,
	customer_id char(6) NOT NULL,
	generation int(2) NOT NULL,
	address text NOT NULL,
	tel varchar(12) NOT NULL,
	lone_start_date date NOT NULL,
	lone_end_date date,
	PRIMARY KEY (lone_id),
	UNIQUE (lone_id),
	UNIQUE (customer_id)
);


CREATE TABLE maker_master
(
	maker_id char(6) NOT NULL,
	maker_name varchar(50) NOT NULL,
	address text NOT NULL,
	tel varchar(12) NOT NULL,
	recode_date date NOT NULL,
	PRIMARY KEY (maker_id),
	UNIQUE (maker_id)
);


CREATE TABLE messages_master
(
	message_id char(3) NOT NULL,
	message text NOT NULL,
	PRIMARY KEY (message_id),
	UNIQUE (message_id)
);


CREATE TABLE order_details_table
(
	num int(4) NOT NULL AUTO_INCREMENT,
	order_id int(12) NOT NULL,
	puroduct_id char(7) NOT NULL,
	puroduct_name varchar(50) NOT NULL,
	price int(8) NOT NULL,
	consumption_tax int(8) NOT NULL,
	amount int(4) NOT NULL,
	PRIMARY KEY (num, order_id),
	UNIQUE (order_id),
	UNIQUE (puroduct_id)
);


CREATE TABLE order_table
(
	order_id int(12) NOT NULL AUTO_INCREMENT,
	estimates_id int(12) NOT NULL,
	customer_id char(6) NOT NULL,
	employment_id int(4) NOT NULL,
	order_date date NOT NULL,
	costomer_name varchar(50) NOT NULL,
	delivery_address text NOT NULL,
	delivery_date date NOT NULL,
	PRIMARY KEY (order_id),
	UNIQUE (order_id),
	UNIQUE (estimates_id),
	UNIQUE (customer_id),
	UNIQUE (employment_id)
);


CREATE TABLE payment_table
(
	num int(2) NOT NULL AUTO_INCREMENT,
	settlement_id int(12) NOT NULL,
	payment_deadline date NOT NULL,
	payment_date date,
	payment_way int(1) NOT NULL,
	paid_price int(12),
	PRIMARY KEY (num, settlement_id),
	UNIQUE (settlement_id)
);


CREATE TABLE personal_customer_master
(
	customer_id char(6) NOT NULL,
	customer_name varchar(50) NOT NULL,
	customer_name_kana varchar(50) NOT NULL,
	postal_code varchar(7) NOT NULL,
	address text NOT NULL,
	tel varchar(12) NOT NULL,
	admission_date date NOT NULL,
	admission_flg int(1) DEFAULT 1 NOT NULL,
	note text,
	PRIMARY KEY (customer_id),
	UNIQUE (customer_id)
);


CREATE TABLE product_unit_table
(
	puroduct_id char(7) NOT NULL,
	unit_id char(6) NOT NULL,
	amount int(3) NOT NULL,
	PRIMARY KEY (puroduct_id, unit_id),
	UNIQUE (puroduct_id),
	UNIQUE (unit_id)
);


CREATE TABLE puroduct_master
(
	puroduct_id char(7) NOT NULL,
	puroduct_name varchar(50) NOT NULL,
	price int(8) NOT NULL,
	category_id int(2) NOT NULL,
	stock int(5) DEFAULT 0 NOT NULL,
	security_stock int(5) DEFAULT 0 NOT NULL,
	next_arrive_date date NOT NULL,
	maker_id char(6) NOT NULL,
	maker_direct_flg int(1) DEFAULT 0 NOT NULL,
	rack_id char(2) NOT NULL,
	PRIMARY KEY (puroduct_id),
	UNIQUE (puroduct_id),
	UNIQUE (category_id),
	UNIQUE (maker_id),
	UNIQUE (rack_id)
);


CREATE TABLE rack_master
(
	rack_id char(2) NOT NULL,
	rack_name varchar(5) NOT NULL,
	PRIMARY KEY (rack_id),
	UNIQUE (rack_id)
);


CREATE TABLE reminder_table
(
	num int(12) NOT NULL AUTO_INCREMENT,
	settlement_id int(12) NOT NULL,
	dunning_date date NOT NULL,
	dunning_flg int(1) DEFAULT 0 NOT NULL,
	note text,
	PRIMARY KEY (num, settlement_id),
	UNIQUE (settlement_id)
);


CREATE TABLE sample_master
(
	sample_id char(10) NOT NULL,
	puroduct_id char(7) NOT NULL,
	sample_name varchar(50) NOT NULL,
	amount int(5) NOT NULL,
	return_flg int(1) NOT NULL,
	PRIMARY KEY (sample_id),
	UNIQUE (sample_id),
	UNIQUE (puroduct_id)
);


CREATE TABLE settlement_table
(
	settlement_id int(12) NOT NULL AUTO_INCREMENT,
	customer_id char(6) NOT NULL,
	request_date date NOT NULL,
	total_fee int(10) NOT NULL,
	consumption_tax int(8) NOT NULL,
	PRIMARY KEY (settlement_id),
	UNIQUE (settlement_id),
	UNIQUE (customer_id)
);


CREATE TABLE shipment_table
(
	order_id int(12) NOT NULL,
	shipment_date date NOT NULL,
	shipment_flg int(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (order_id),
	UNIQUE (order_id)
);


CREATE TABLE stock_table
(
	stock_id int(12) NOT NULL AUTO_INCREMENT,
	puroduct_id char(7) NOT NULL,
	product_name varchar(50) NOT NULL,
	stock_amount int(5) DEFAULT 0 NOT NULL,
	provision_stock int(5) DEFAULT 0 NOT NULL,
	entry_date datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (stock_id),
	UNIQUE (stock_id),
	UNIQUE (puroduct_id)
);


CREATE TABLE suppliers_master
(
	-- 法人顧客IDまたはメーカーIDが格納される。
	-- ※法人顧客フラグが建っている場合は前者、メーカーフラグが建っている場合は後者となる。
	suppliers_id char(6) NOT NULL COMMENT '法人顧客IDまたはメーカーIDが格納される。
※法人顧客フラグが建っている場合は前者、メーカーフラグが建っている場合は後者となる。',
	customer_flg int(1) DEFAULT 0 NOT NULL,
	marker_flg int(1) DEFAULT 0 NOT NULL,
	postal_code varchar(7) NOT NULL,
	prefectures varchar(4) NOT NULL,
	address text NOT NULL,
	tel varchar(12) NOT NULL,
	PRIMARY KEY (suppliers_id)
);


CREATE TABLE unit_master
(
	unit_id char(6) NOT NULL,
	unit_name varchar(10) NOT NULL,
	PRIMARY KEY (unit_id),
	UNIQUE (unit_id)
);



/* Create Foreign Keys */

ALTER TABLE puroduct_master
	ADD FOREIGN KEY (category_id)
	REFERENCES category_master (category_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE lone_table
	ADD FOREIGN KEY (customer_id, generation)
	REFERENCES corporation_customer_master (customer_id, generation)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE corporation_customer_master
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE earnings_table
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE estimates_table
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE order_table
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE personal_customer_master
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE settlement_table
	ADD FOREIGN KEY (customer_id)
	REFERENCES customers_distinction_master (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE employment_master
	ADD FOREIGN KEY (department_id)
	REFERENCES department_master (department_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE earnings_detail_table
	ADD FOREIGN KEY (earning_id)
	REFERENCES earnings_table (earning_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE earnings_table
	ADD FOREIGN KEY (employment_id)
	REFERENCES employment_master (employment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE estimates_table
	ADD FOREIGN KEY (employment_id)
	REFERENCES employment_master (employment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE order_table
	ADD FOREIGN KEY (employment_id)
	REFERENCES employment_master (employment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE estimates_detail_table
	ADD FOREIGN KEY (estimates_id)
	REFERENCES estimates_table (estimates_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE order_table
	ADD FOREIGN KEY (estimates_id)
	REFERENCES estimates_table (estimates_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE lending_detail_table
	ADD FOREIGN KEY (lone_id)
	REFERENCES lone_table (lone_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE puroduct_master
	ADD FOREIGN KEY (maker_id)
	REFERENCES maker_master (maker_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE delivering_table
	ADD FOREIGN KEY (order_id)
	REFERENCES order_table (order_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE earnings_table
	ADD FOREIGN KEY (order_id)
	REFERENCES order_table (order_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE order_details_table
	ADD FOREIGN KEY (order_id)
	REFERENCES order_table (order_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE shipment_table
	ADD FOREIGN KEY (order_id)
	REFERENCES order_table (order_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE earnings_detail_table
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE estimates_detail_table
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE inventories_master
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE order_details_table
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE product_unit_table
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sample_master
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE stock_table
	ADD FOREIGN KEY (puroduct_id)
	REFERENCES puroduct_master (puroduct_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE puroduct_master
	ADD FOREIGN KEY (rack_id)
	REFERENCES rack_master (rack_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE lending_detail_table
	ADD FOREIGN KEY (sample_id)
	REFERENCES sample_master (sample_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE payment_table
	ADD FOREIGN KEY (settlement_id)
	REFERENCES settlement_table (settlement_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reminder_table
	ADD FOREIGN KEY (settlement_id)
	REFERENCES settlement_table (settlement_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE estimates_detail_table
	ADD FOREIGN KEY (stock_id)
	REFERENCES stock_table (stock_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE product_unit_table
	ADD FOREIGN KEY (unit_id)
	REFERENCES unit_master (unit_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



