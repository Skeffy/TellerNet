START TRANSACTION;

INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES ('Noah', 'Stevens', '555-555-5555', '123 Main St', 'dontstealmymoney@gmail.com', '07-22-1998');
INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES ('John', 'Doe', '123-456-7890', '8290 4th Ave', 'myemail@aol.com', '11/18/1985');
INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES ('Jane', 'Doe', '649-285-4497', '23 Street Blvd', 'plsnospam@yahoo.com', '04/09/2000');
INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES ('Joseph', 'Bank', '518-348-3481', '505 Main St', 'originalbanker@bing.com', '06/16/1996');
INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES ('Molly', 'Smith', '724-445-1687', '4756 House Rd', 'msmith@gmail.com', '12-04-1975');

INSERT INTO account (customer_id, nickname) VALUES (1, 'checking');
INSERT INTO account (customer_id, nickname) VALUES (1, 'savings');
INSERT INTO account (customer_id, nickname) VALUES (2, 'fun money');
INSERT INTO account (customer_id, nickname) VALUES (3, 'secret stash');
INSERT INTO account (customer_id, nickname) VALUES (4, 'savings');
INSERT INTO account (customer_id, nickname) VALUES (5, 'checking');
INSERT INTO account (customer_id) VALUES (3);

COMMIT;