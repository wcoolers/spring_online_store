CREATE TABLE `role`( 
	role_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	role_name VARCHAR(30) NOT NULL UNIQUE 
);
CREATE TABLE `user` ( 
	user_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	email VARCHAR(75) NOT NULL UNIQUE, 
	encrypted_password VARCHAR(128) NOT NULL, 
	enabled BIT NOT NULL  
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
	user_id BIGINT NOT NULL,
	PRIMARY KEY(user_id, customer_id),
	UNIQUE(customer_id)
);

CREATE TABLE `order`( 
	order_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	order_date DATE NOT NULL,
	order_status VARCHAR(45) NOT NULL,
	customer_id BIGINT NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

ALTER TABLE user_role 
ADD CONSTRAINT user_role_fk1 FOREIGN KEY (user_id) 
REFERENCES `user` (user_id); 

ALTER TABLE user_role 
ADD CONSTRAINT user_role_fk2 FOREIGN KEY (role_Id) 
REFERENCES role (role_id);

ALTER TABLE customer 
ADD CONSTRAINT customer_fk1 FOREIGN KEY (user_id) 
REFERENCES `user` (user_id);
