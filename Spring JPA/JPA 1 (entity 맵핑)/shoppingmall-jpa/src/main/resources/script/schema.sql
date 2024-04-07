CREATE TABLE IF NOT EXISTS Categories (
    CategoryID   BIGINT AUTO_INCREMENT,
    CategoryName VARCHAR(50) NOT NULL,
    PRIMARY KEY (CategoryID)
    );

CREATE TABLE IF NOT EXISTS Product_Categories (
    CategoryID BIGINT NOT NULL,
    ProductID  BIGINT NOT NULL,
    PRIMARY KEY (ProductID, CategoryID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
    );

CREATE TABLE IF NOT EXISTS Products (
    ProductID    BIGINT AUTO_INCREMENT,
    ModelNumber  VARCHAR(10) NOT NULL,
    ModelName    VARCHAR(120) NOT NULL,
    ProductImage TEXT,
    UnitCost     DECIMAL(15,2) NOT NULL,
    Description  TEXT,
    PRIMARY KEY (ProductID)
    );

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

CREATE TABLE IF NOT EXISTS UserAddress (
    AddressID   BIGINT AUTO_INCREMENT,
    user_id     VARCHAR(50) NOT NULL,
    AddressName VARCHAR(100) NOT NULL,
    PRIMARY KEY (AddressID, user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

CREATE TABLE IF NOT EXISTS ShoppingCart (
    ProductID BIGINT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    amount    INT NOT NULL,
    PRIMARY KEY (ProductID, user_id),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

CREATE TABLE IF NOT EXISTS Orders (
    OrderID   INT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    OrderDate DATETIME NULL,
    ShipDate  DATETIME NULL,
    PRIMARY KEY (OrderID, user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

CREATE TABLE IF NOT EXISTS OrderDetails (
    OrderID   INT NOT NULL,
    user_id   VARCHAR(50) NOT NULL,
    ProductID BIGINT NULL,
    Quantity  INT NULL,
    UnitCost  DECIMAL(15, 2) NULL,
    PRIMARY KEY (OrderID, user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
    );