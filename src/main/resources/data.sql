
INSERT INTO `user` (email, encrypted_password, enabled)
VALUES ('user@user.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO `user` (email, encrypted_password, enabled)
VALUES ('guest@guest.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO `user` (email, encrypted_password, enabled)
VALUES ('admin@admin.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO `user` (email, encrypted_password, enabled)
VALUES ('manager@manager.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO `role` (role_name)
VALUES ('ROLE_USER');
 
INSERT INTO `role` (role_name)
VALUES ('ROLE_GUEST');

INSERT INTO `role` (role_name)
VALUES ('ROLE_ADMIN');

INSERT INTO `role` (role_name)
VALUES ('ROLE_MANAGER');
 
 
INSERT INTO `user_role` (user_id, role_id)
VALUES (1, 1);
 
INSERT INTO `user_role` (user_id, role_id)
VALUES (2, 2);

INSERT INTO `user_role` (user_id, role_id)
VALUES (3, 3);

INSERT INTO `user_role` (user_id, role_id)
VALUES (4, 4);

INSERT INTO `customer` (first_name, last_name, user_id)
VALUES ('User', 'Philips', 1);

INSERT INTO `customer` (first_name, last_name, user_id)
VALUES ('Guest', 'Akanbi', 2);

INSERT INTO `customer` (first_name, last_name, user_id)
VALUES ('Admin', 'Lampard', 3);

INSERT INTO `customer` (first_name, last_name, user_id)
VALUES ('Manager', 'Paul', 4);

INSERT INTO `order` (order_date, order_status, customer_id, order_amount)
VALUES (CURRENT_DATE(), 'pending', 2, 12.95);

INSERT INTO `order` (order_date, order_status, customer_id, order_amount)
VALUES (CURRENT_DATE(), 'pending', 1, 41.93);

INSERT INTO `order` (order_date, order_status, customer_id, order_amount)
VALUES (CURRENT_DATE(), 'pending', 2, 8.91);

INSERT INTO `order` (order_date, order_status, customer_id, order_amount)
VALUES (CURRENT_DATE(), 'pending', 1, 19.08);

INSERT INTO `address`(street_name, street_number, city, province, postal_code) 
VALUES('Dundas Street West', '11', 'Oakville', 'Ontario', 'L7J 0P5');

INSERT INTO `address`(street_name, street_number, city, province, postal_code) 
VALUES('James Street', '41', 'Mississauga', 'Ontario', 'P6M 2W9');

INSERT INTO `address`(street_name, street_number, city, province, postal_code) 
VALUES('Funnas Street West', '55', 'Hamilton', 'Ontario', 'M1K 0W2');

INSERT INTO `address`(street_name, street_number, city, province, postal_code) 
VALUES('Dano Street', '2', 'Burlington', 'Ontario', 'A7M 4A3');

INSERT INTO `product`(product_name, product_price) 
VALUES('Pencil', '2.59');

INSERT INTO `product`(product_name, product_price) 
VALUES('Drawing Board', '5.99');

INSERT INTO `product`(product_name, product_price) 
VALUES('Paper', '0.99');

INSERT INTO `product`(product_name, product_price) 
VALUES('Erazer', '1.59');

INSERT INTO `customer_address`(customer_id, user_id, address_id) 
VALUES('1', '1', '1');

INSERT INTO `customer_address`(customer_id, user_id, address_id) 
VALUES('2', '2', '2');

INSERT INTO `customer_address`(customer_id, user_id, address_id) 
VALUES('3', '3', '3');

INSERT INTO `customer_address`(customer_id, user_id, address_id) 
VALUES('4', '4', '4');

INSERT INTO `order_product`(order_id, product_id, quantity) 
VALUES('1', '1', '5');

INSERT INTO `order_product`(order_id, product_id, quantity) 
VALUES('2', '2', '7');

INSERT INTO `order_product`(order_id, product_id, quantity) 
VALUES('3', '3', '9');

INSERT INTO `order_product`(order_id, product_id, quantity) 
VALUES('4', '4', '12');

