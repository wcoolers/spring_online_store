CREATE TABLE `user` ( 
	user_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	email VARCHAR(75) NOT NULL UNIQUE, 
	encrypted_password VARCHAR(128) NOT NULL, 
	enabled BIT NOT NULL  
); 

CREATE TABLE `role`( 
	role_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	role_name VARCHAR(30) NOT NULL UNIQUE 
);

CREATE TABLE `user_role` 
( 
	user_id BIGINT NOT NULL, 
	role_id BIGINT NOT NULL,
	PRIMARY KEY(user_id, role_id)
); 

CREATE TABLE customer( 
	customer_id BIGINT NOT NULL AUTO_INCREMENT, 
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL,
	user_id BIGINT NOT NULL UNIQUE,
	PRIMARY KEY(user_id, customer_id),
	UNIQUE(customer_id)
);

CREATE TABLE `address` (
	address_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	street_name VARCHAR(45) NOT NULL,
	street_number VARCHAR(45) NOT NULL,
	city VARCHAR(45) NOT NULL,
	province VARCHAR(45) NOT NULL,
	postal_code VARCHAR(45) NOT NULL
);

CREATE TABLE `customer_address` (
	customer_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	address_id BIGINT NOT NULL,
	PRIMARY KEY(customer_id, user_id, address_id)
);

CREATE TABLE `order`( 
	order_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	order_date DATE NOT NULL,
	order_status VARCHAR(45) NOT NULL,
	customer_id BIGINT NOT NULL,
	order_amount DOUBLE NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
	ON DELETE CASCADE
);

CREATE TABLE `product`( 
	product_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	product_name VARCHAR(100) NOT NULL UNIQUE,
	product_price DOUBLE NOT NULL
);

CREATE TABLE `order_product`( 
	order_id BIGINT NOT NULL, 
	product_id BIGINT NOT NULL,
	quantity INTEGER NOT NULL,
	PRIMARY KEY(order_id, product_id),
	FOREIGN KEY (order_id) REFERENCES `order`(order_id) ON DELETE CASCADE,
	FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);


ALTER TABLE user_role 
ADD CONSTRAINT user_role_fk1 FOREIGN KEY (user_id) 
REFERENCES `user` (user_id)
ON DELETE CASCADE; 

ALTER TABLE user_role 
ADD CONSTRAINT user_role_fk2 FOREIGN KEY (role_Id) 
REFERENCES `role` (role_id)
ON DELETE CASCADE;

ALTER TABLE customer 
ADD CONSTRAINT customer_fk1 FOREIGN KEY (user_id) 
REFERENCES `user` (user_id)
ON DELETE CASCADE;

ALTER TABLE customer_address
ADD CONSTRAINT customer_address_fk1 FOREIGN KEY (customer_id) 
REFERENCES customer (customer_id)
ON DELETE CASCADE;

ALTER TABLE customer_address
ADD CONSTRAINT customer_address_fk2 FOREIGN KEY (user_id) 
REFERENCES `user` (user_id)
ON DELETE CASCADE;

ALTER TABLE customer_address
ADD CONSTRAINT customer_address_fk3 FOREIGN KEY (address_id) 
REFERENCES `address` (address_id)
ON DELETE CASCADE;