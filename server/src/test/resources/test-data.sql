START TRANSACTION;

INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES
	('Noah', 'Stevens', '555-555-5555', '123 Main St', 'dontstealmymoney@gmail.com', '07-22-1998'),
	('John', 'Doe', '123-456-7890', '8290 4th Ave', 'myemail@aol.com', '11/18/1985'),
	('Jane', 'Doe', '649-285-4497', '23 Street Blvd', 'plsnospam@yahoo.com', '04/09/2000'),
	('Joseph', 'Bank', '518-348-3481', '505 Main St', 'originalbanker@bing.com', '06/16/1996'),
	('Molly', 'Smith', '724-445-1687', '4756 House Rd', 'msmith@gmail.com', '12-04-1975');

INSERT INTO account (customer_id, nickname, balance) VALUES
	(1, 'checking', 0.00),
	(1, 'savings', 0.00),
	(2, 'fun money', 0.00),
	(3, 'secret stash', 0.00),
	(5, 'savings', 0.00),
	(5, 'checking', 0.00),
	(3, '', 1056.62);

INSERT INTO transaction (account_id, amount, description, transaction_date) VALUES
	(2, 100.00, 'Cash deposit', '10-11-2024'),
	(2, 738.55, 'Payroll deposit', '10-25-2024'),
	(2, -34.18, 'Uber eats', '10-28-2024'),
	(3, 500.00, 'Cash deposit', '9-18-2024'),
	(4, 350.00, 'Cash deposit', '9-3-2024'),
	(4, -83.57, 'Amazon.com', '10-2-2024');

COMMIT;