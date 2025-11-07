-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pharmacy_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pharmacy_db` ;

-- -----------------------------------------------------
-- Schema pharmacy_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pharmacy_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `pharmacy_db` ;

-- -----------------------------------------------------
-- Table `pharmacy_db`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`customer` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`customer` (
  `customerID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`cashcustomer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`cashcustomer` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`cashcustomer` (
  `customerID` INT(11) NOT NULL,
  `paymentMethod` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`customerrecords`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`customerrecords` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`customerrecords` (
  `customerID` INT(11) NOT NULL,
  `visitDate` DATETIME NULL DEFAULT NULL,
  `notes` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`distributingcompany`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`distributingcompany` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`distributingcompany` (
  `companyID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `location` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`companyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`product` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`product` (
  `productID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `stockQuantity` INT(11) NULL DEFAULT NULL,
  `expiryDate` DATE NULL DEFAULT NULL,
  `isPrescriptionRequired` TINYINT(1) NULL DEFAULT NULL,
  `companyID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`productID`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`drug`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`drug` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`drug` (
  `productID` INT(11) NOT NULL,
  `dosage` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`productID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`employee` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`employee` (
  `employeeID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(100) NULL DEFAULT NULL,
  `role` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`employeeID`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`inventory` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`inventory` (
  `inventoryID` INT(11) NOT NULL AUTO_INCREMENT,
  `lastUpdateDate` DATE NULL DEFAULT NULL,
  `productID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`inventoryID`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`manager`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`manager` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`manager` (
  `managerID` INT(11) NOT NULL,
  PRIMARY KEY (`managerID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`otheritem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`otheritem` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`otheritem` (
  `productID` INT(11) NOT NULL,
  `category` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`productID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`sale` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`sale` (
  `saleID` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `totalAmount` DOUBLE NULL DEFAULT NULL,
  `employeeID` INT(11) NULL DEFAULT NULL,
  `customerID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`saleID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pharmacy_db`.`sale_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pharmacy_db`.`sale_items` ;

CREATE TABLE IF NOT EXISTS `pharmacy_db`.`sale_items` (
  `saleID` INT(11) NOT NULL,
  `productID` INT(11) NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`saleID`, `productID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
