CREATE TABLE shop (
	id INT PRIMARY KEY AUTO_INCREMENT,
	identifier VARCHAR(255) NOT NULL,
	status VARCHAR(255) NOT NULL,
	date_shop TIMESTAMP
);

CREATE TABLE shop_item (
	id INT PRIMARY KEY AUTO_INCREMENT,
	product_identifier VARCHAR(100) NOT NULL,
	amount INT NOT NULL,
	price FLOAT NOT NULL,
	shop_id BIGINT REFERENCES shop(id)
);
