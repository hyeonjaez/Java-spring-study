CREATE TABLE IF NOT EXISTS Categories (
    CategoryID   BIGINT AUTO_INCREMENT,
    CategoryName VARCHAR(50) NOT NULL,
    PRIMARY KEY (CategoryID)
    );

-- MERGE INTO Categories USING (VALUES (1, 'Electronics'), (2, 'Clothing'), (3, 'Books'))
--     AS new_data(CategoryID, CategoryName)
--     ON Categories.CategoryID = new_data.CategoryID
--     WHEN NOT MATCHED THEN
--         INSERT (CategoryID, CategoryName) VALUES (new_data.CategoryID, new_data.CategoryName);

CREATE TABLE IF NOT EXISTS Product_Categories (
    CategoryID BIGINT NOT NULL,
    ProductID  BIGINT NOT NULL,
    PRIMARY KEY (ProductID, CategoryID)
    );

-- MERGE INTO Product_Categories USING (VALUES (1, 1), (2, 1), (3, 2))
--     AS new_data(CategoryID, ProductID)
--     ON Product_Categories.CategoryID = new_data.CategoryID AND Product_Categories.ProductID = new_data.ProductID
--     WHEN NOT MATCHED THEN
--         INSERT (CategoryID, ProductID) VALUES (new_data.CategoryID, new_data.ProductID);

CREATE TABLE IF NOT EXISTS Products (
    ProductID    BIGINT AUTO_INCREMENT,
    ModelNumber  VARCHAR(10) NOT NULL,
    ModelName    VARCHAR(120) NOT NULL,
    ProductImage TEXT,
    UnitCost     DECIMAL(15,2) NOT NULL,
    Description  TEXT,
    PRIMARY KEY (ProductID)
    );

-- INSERT INTO Products (ModelNumber, ModelName, ProductImage, UnitCost, Description)
-- VALUES
--     ('M001', 'Smartphone', 'smartphone_x.jpg', 10000, 'A high-end smartphone with advanced features.'),
--     ('M002', 'Laptop', 'laptop_pro.jpg', 5000, 'Powerful laptop for professional use.'),
--     ('M003', 'Camera', 'camera_z.jpg', 6000, 'High-resolution camera with advanced optics.'),
--     ('M004', 'Headphones', 'headphones_a.jpg', 3000, 'Premium headphones with noise-canceling technology.'),
--     ('M005', 'Tablet', 'tablet_s.jpg', 12000, 'Sleek and lightweight tablet for on-the-go.'),
--     ('M006', 'Smartwatch', 'smartwatch_w.jpg', 4000, 'Smartwatch with fitness tracking and health monitoring.');
--




-- MERGE INTO Products USING (VALUES (1, 'M001', 'Smartphone', 'smartphone_image.jpg', 599.99, 'High-end smartphone'),
--                                   (2, 'C002', 'T-Shirt', 'tshirt_image.jpg', 19.99, 'Casual cotton T-shirt'),
--                                   (3, 'B003', 'Java Programming Book', NULL, 49.99, 'Comprehensive guide to Java programming'))
--     AS new_data(ProductID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
--     ON Products.ProductID = new_data.ProductID
--     WHEN NOT MATCHED THEN
--         INSERT (ProductID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
--             VALUES (new_data.ProductID, new_data.ModelNumber, new_data.ModelName, new_data.ProductImage, new_data.UnitCost, new_data.Description);


CREATE TABLE IF NOT EXISTS users (
    user_id          VARCHAR(50) NOT NULL,
    user_name        VARCHAR(50) NOT NULL,
    user_password    VARCHAR(200) NOT NULL,
    user_birth       VARCHAR(8),
    user_auth        VARCHAR(10) NOT NULL,
    user_point       INT NOT NULL,
    created_at       DATETIME NOT NULL,
    latest_login_at  DATETIME DEFAULT NULL,
    PRIMARY KEY (user_id)
    );

MERGE INTO users KEY (user_id) VALUES ( 'user','유저1','1234','19990101','유저',100000,'2023-01-01',null);

-- MERGE INTO users USING (VALUES ('user1', 'John Doe', 'password123', '19900101', 'ROLE_USER', 1000000, CURRENT_TIMESTAMP, NULL),
--                                ('admin1', 'Admin User', 'adminpass', '19851215', 'ROLE_ADMIN', 1000000, CURRENT_TIMESTAMP, NULL))
--     AS new_data(user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at)
--     ON users.user_id = new_data.user_id
--     WHEN NOT MATCHED THEN
--         INSERT (user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at)
--             VALUES (new_data.user_id, new_data.user_name, new_data.user_password, new_data.user_birth, new_data.user_auth, new_data.user_point, new_data.created_at, new_data.latest_login_at);

CREATE TABLE IF NOT EXISTS UserAddress (
    AddressID   BIGINT AUTO_INCREMENT,
    user_id     VARCHAR(50) NOT NULL,
    AddressName VARCHAR(100) NOT NULL,
    PRIMARY KEY (AddressID, user_id)
    );

CREATE TABLE IF NOT EXISTS ShoppingCart (
    ProductID BIGINT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    amount    INT NOT NULL,
    PRIMARY KEY (ProductID, user_id)
    );
