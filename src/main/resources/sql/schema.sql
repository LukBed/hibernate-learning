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

DROP TABLE IF EXISTS review;
CREATE TABLE IF NOT EXISTS review (
    id SERIAL NOT NULL,
    product_id INTEGER NOT NULL,
    content VARCHAR(400) NULL,
    rating INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_review_product
    FOREIGN KEY (product_id)
    REFERENCES product (id)
    ON DELETE CASCADE --delete product -> delete reviews also
    ON UPDATE CASCADE
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
id SERIAL NOT NULL,
name VARCHAR(100) NULL,
description VARCHAR(800) NULL,
PRIMARY KEY (id)
);

ALTER TABLE product
ADD COLUMN category_id INTEGER NULL;

