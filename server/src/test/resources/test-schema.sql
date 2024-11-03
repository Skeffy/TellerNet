START TRANSACTION;

DROP TABLE if EXISTS customer, account, transaction;

CREATE TABLE customer (
	customer_id SERIAL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone VARCHAR(12) NOT NULL,
	address VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	dob DATE NOT NULL,
	CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

CREATE TABLE account (
	account_id SERIAL,
	customer_id INT NOT NULL,
	nickname VARCHAR(50),
	balance NUMERIC(10, 2) DEFAULT 0.00,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE transaction (
	transaction_id SERIAL,
	account_id INT NOT NULL,
	transaction_date DATE DEFAULT CURRENT_DATE,
	amount NUMERIC(10, 2) NOT NULL,
	description VARCHAR(100) NOT NULL,
	CONSTRAINT PK_transaction PRIMARY KEY (transaction_id),
	CONSTRAINT FK_transaction_account FOREIGN KEY (account_id) REFERENCES account(account_id)
);

COMMIT;