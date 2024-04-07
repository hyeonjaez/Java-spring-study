CREATE TABLE Categories (
                            CategoryID		INT	auto_increment,
                            CategoryName	varchar(50),

                            CONSTRAINT pk_Categories PRIMARY KEY(CategoryID)
);


CREATE TABLE `Product_Categories` (
	`CategoryID` INT	NOT NULL,
	`ProductID`	INT	NOT NULL,
    PRIMARY KEY (ProductID, CategoryID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Products (
						  ProductID	INT	auto_increment,
                          ModelNumber	nvarchar(10),
                          ModelName	nvarchar(120),
                          ProductImage	text,
                          UnitCost	decimal(15),
                          Description	text,

                          CONSTRAINT pk_Products PRIMARY KEY(ProductID)
);



CREATE TABLE `users` (
  `user_id` varchar(50) NOT NULL COMMENT '아이디',
  `user_name` varchar(50) NOT NULL COMMENT '이름',
  `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
  `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
  `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
  `user_point` int NOT NULL COMMENT 'default : 1000000',
  `created_at` datetime NOT NULL COMMENT '가입일자',
  `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';


CREATE TABLE `UserAddress` (
    `AddressID` INT AUTO_INCREMENT,
    `user_id` VARCHAR(50) NOT NULL COMMENT '아이디',
    `AddressName` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`AddressID`, `user_id`),
    CONSTRAINT `FK_user_TO_UserAddress_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);


CREATE TABLE `ShoppingCart` (
	`ProductID`	INT	NOT NULL,
	`user_id`	varchar(50)	NOT NULL	COMMENT '아이디',
	`amount`	INT	NOT NULL,
	PRIMARY KEY (`ProductID`, `user_id`),
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`),
	FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

