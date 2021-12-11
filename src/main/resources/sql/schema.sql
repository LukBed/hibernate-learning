DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id SERIAL NOT NULL,
    name VARCHAR(100) NULL,
    description VARCHAR(800) NULL,
    created TIMESTAMP,
    updated TIMESTAMP,
    price DECIMAL(5,2),
    productType VARCHAR(10),
    PRIMARY KEY (id)
);