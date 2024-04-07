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

CREATE TABLE IF NOT EXISTS Orders (
    OrderID   INT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    OrderDate DATETIME NULL,
    ShipDate  DATETIME NULL,
    PRIMARY KEY (OrderID, user_id)
    );

CREATE TABLE IF NOT EXISTS OrderDetails (
    OrderID   INT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    ProductID BIGINT NULL,
    Quantity  INT NULL,
    UnitCost  DECIMAL(15, 2) NULL,
    PRIMARY KEY (OrderID, user_id)
    );