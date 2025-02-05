CREATE TABLE IF NOT EXISTS product (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    discount_price DOUBLE,
    description TEXT,
    quantity INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS category (
    id VARCHAR(255) PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS product_category (
    product_id VARCHAR(255),
    category_id VARCHAR(255),
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS product_color (
    product_id VARCHAR(255),
    product_colors VARCHAR(50) NOT NULL,
    PRIMARY KEY (product_id, product_colors),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS product_size (
    product_id VARCHAR(255),
    product_sizes VARCHAR(50) NOT NULL,
    PRIMARY KEY (product_id, product_sizes),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS image (
    id VARCHAR(255) PRIMARY KEY,
    image_path VARCHAR(255) NOT NULL,
    is_main_image BOOLEAN NOT NULL,
    product_id VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
    );

