create database reservation_system;
use reservation_system;

drop table RESERVATION_USER_COMMENT_IMAGE;
drop table RESERVATION_USER_COMMENT;
drop table RESERVATION_INFO;
drop table PRODUCT_IMAGE;
drop table FILE;
drop table PRODUCT_PRICE;
drop table PRODUCT_DETAIL;
drop table DISPLAY_INFO;
drop table PRODUCT;
drop table CATEGORY;
drop table USERS;

-- naver  ���� �α����� ���� USERS���̺�  : https://developers.naver.com/docs/login/devguide/ �űԽý��� �׾�(���̹��α���)�� �̿� �κ��� ������
CREATE  TABLE `USERS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL ,
  `email` VARCHAR(100) NULL ,
  `tel` VARCHAR(50) NULL ,
  `nickname` VARCHAR(50) NULL ,
  `sns_id` VARCHAR(255) NULL ,
  `sns_type` varchar(10)  NULL,
  `sns_profile` varchar(255)  NULL,
  `admin_flag` INT NOT NULL,
  `create_date` DATETIME NULL ,
  `modify_date` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx1_username` (`username` ASC) ,
  INDEX `idx2_email` (`email` ASC),
  INDEX `idx3_sns_id` (`sns_id` ASC)
);

CREATE TABLE CATEGORY(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY(`id`)
);


-- ��ǰ �⺻����, ��������, ������
-- ��ǰ�Ǹſ��� : sales_flag - 0 : �Ǹžȵ� 1: �Ǹ� ��
CREATE TABLE PRODUCT (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL,
  `sales_start` DATETIME NOT NULL,
  `sales_end` DATETIME NULL,
  `sales_flag` INT(1) NOT NULL,
  `event` VARCHAR(4000),
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`category_id`) REFERENCES CATEGORY(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DISPLAY_INFO(
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `observation_time` VARCHAR(100) NOT NULL,
  `display_start` DATETIME NOT NULL,
  `display_end` DATETIME NOT NULL,
  `place_name` VARCHAR(50) NOT NULL,
  `place_lot` VARCHAR(100) ,
  `place_street` VARCHAR(100),
  `tel` VARCHAR(20) ,
  `homepage` VARCHAR(255),
  `email` VARCHAR(255),
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE PRODUCT_DETAIL(
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `content` TEXT,
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- price_type : 1, �Ϲ�  2 :û�ҳ� 3: ��� , CODE ���̺��� ��� �ʿ��ϴ�.
CREATE TABLE PRODUCT_PRICE (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `price_type` INT NOT NULL,
  `price` INT NOT NULL,
  `discount_rate` DECIMAL(5,2) NOT NULL,
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE FILE (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  `save_file_name` VARCHAR(4000) NOT NULL,
  `file_length` INT NOT NULL,
  `content_type` VARCHAR(255) NOT NULL,
  `delete_flag` INT(1) NOT NULL,
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`user_id`) REFERENCES USERS(`id`)
);

-- type : ��ǥ�̹��� - 1 , �ΰ��̹��� - 2
-- delete_flag 0 :�����ȵ�, 1: ������ �̹���
CREATE TABLE PRODUCT_IMAGE (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `file_id` INT NOT NULL,
  `type` INT(1) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`file_id`) REFERENCES FILE(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


-- ������ ��ǰ�� �������� �����ϵ��� �� ���ΰ�? �ƴϸ� �׳� ��ǰ id���� �������� �� ���ΰ�?
CREATE TABLE RESERVATION_INFO(
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `general_ticket_count` INT,
  `youth_ticket_count` INT,
  `child_ticket_count` INT,
  `reservation_name` VARCHAR(50) NOT NULL,
  `reservation_tel` VARCHAR(255) NOT NULL,
  `reservation_email` VARCHAR(255) NOT NULL,
  `reservation_date` DATETIME NOT NULL,
  `reservation_type` INT,
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`user_id`) REFERENCES USERS(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE RESERVATION_USER_COMMENT (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `score` DECIMAL(2,1) NOT NULL,
  `comment` TEXT NOT NULL,
  `create_date` DATETIME,
  `modify_date` DATETIME,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`product_id`) REFERENCES PRODUCT(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`user_id`) REFERENCES USERS(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE RESERVATION_USER_COMMENT_IMAGE (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_user_comment_id` INT NOT NULL,
  `file_id` INT NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`reservation_user_comment_id`) REFERENCES RESERVATION_USER_COMMENT(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`file_id`) REFERENCES FILE(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


ALTER TABLE reservation_info MODIFY reservation_type ENUM('REQUESTING', 'DUE', 'USED', 'REFUND_CANCEL');