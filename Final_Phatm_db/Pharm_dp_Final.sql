USE PharmacyDB;

-- مسح الجداول لو موجودة
DROP TABLE IF EXISTS CustomerRecords;
DROP TABLE IF EXISTS CustomerOrders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS SaleItems;
DROP TABLE IF EXISTS Sale;
DROP TABLE IF EXISTS CompanyProducts;
DROP TABLE IF EXISTS DistributingCompany;
DROP TABLE IF EXISTS Manager;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Product;

-- إنشاء الجداول من جديد
CREATE TABLE Product (
    productID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL CHECK (price >= 0),
    stockQuantity INT NOT NULL CHECK (stockQuantity >= 0),
    expiryDate DATE NOT NULL,
    isPrescriptionRequired BOOLEAN NOT NULL,
    dosage VARCHAR(50) DEFAULT NULL
);

CREATE TABLE Employee (
    employeeID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    totalSales DOUBLE DEFAULT 0
);

CREATE TABLE Manager (
    managerID VARCHAR(10) PRIMARY KEY,
    FOREIGN KEY (managerID) REFERENCES Employee(employeeID)
);

CREATE TABLE DistributingCompany (
    companyID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    location VARCHAR(100)
);

CREATE TABLE CompanyProducts (
    companyID VARCHAR(10),
    productID INT,
    PRIMARY KEY (companyID, productID),
    FOREIGN KEY (companyID) REFERENCES DistributingCompany(companyID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);

CREATE TABLE Sale (
    saleID VARCHAR(10) PRIMARY KEY,
    employeeID VARCHAR(10),
    saleDate DATE NOT NULL,
    totalAmount DOUBLE NOT NULL,
    payment VARCHAR(50) NOT NULL,
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID)
);

CREATE TABLE SaleItems (
    saleID VARCHAR(10),
    productID INT,
    quantity INT NOT NULL,
    PRIMARY KEY (saleID, productID),
    FOREIGN KEY (saleID) REFERENCES Sale(saleID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);

CREATE TABLE Customer (
    customerID INT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(100)
);

CREATE TABLE CustomerOrders (
    customerID INT,
    productID INT,
    PRIMARY KEY (customerID, productID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);

CREATE TABLE CustomerRecords (
    customerID INT PRIMARY KEY,
    amountDue DOUBLE DEFAULT 0,
    lastPaymentDate DATE,
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);
-- =========================
-- إضافة المنتجات
-- =========================
INSERT INTO Product (productID, name, category, price, stockQuantity, expiryDate, isPrescriptionRequired, dosage) VALUES
(1, 'Panadol', 'Medicine', 30.0, 100, '2026-11-22', FALSE, '500mg'),
(2, 'Augmentin', 'Medicine', 80.0, 50, '2026-05-22', TRUE, '625mg'),
(3, 'Head&Shoulders', 'Hair Care', 405.5, 20, '2026-11-22', FALSE, NULL),
(4, 'Ibuprofen', 'Medicine', 25.0, 300, '2027-05-22', FALSE, '200mg'),
(5, 'Cetirizine', 'Medicine', 1005.0, 130, '2027-11-22', FALSE, '10mg'),
(6, 'Colgate', 'Oral Care', 50.0, 30, '2027-11-22', FALSE, NULL),
(7, 'Nivea Face Cream', 'Skin Care', 1220.0, 15, '2026-11-22', FALSE, NULL);

-- =========================
-- إضافة الموظفين
-- =========================
INSERT INTO Employee (employeeID, name, username, password, role, totalSales) VALUES
('E001', 'Abdullah', 'AbdullahUser', 'password123', 'Pharmacist', 0),
('E002', 'Ali', 'AliUser', 'password456', 'Pharmacist', 0);

-- =========================
-- إضافة المدير
-- =========================
INSERT INTO Manager (managerID) VALUES
('M001');  -- لازم يكون موجود في Employee كـ ID

-- =========================
-- إضافة الشركات
-- =========================
INSERT INTO DistributingCompany (companyID, name, email, location) VALUES
('C001', 'MedLife Pharma', 'info@medlife.com', 'Cairo'),
('C002', 'HealthyMed Co', 'contact@healthymed.com', 'Giza');

-- =========================
-- ربط المنتجات بالشركات
-- =========================
INSERT INTO CompanyProducts (companyID, productID) VALUES
('C001', 1),
('C001', 2),
('C002', 4),
('C002', 5);

-- =========================
-- إضافة العملاء
-- =========================
INSERT INTO Customer (customerID, name, phone, address) VALUES
(1, 'Mohamed', '01012345678', 'Cairo'),
(2, 'Sara', '01234567890', 'Giza');

-- =========================
-- إضافة سجلات العملاء
-- =========================
INSERT INTO CustomerRecords (customerID, amountDue, lastPaymentDate) VALUES
(1, 20000.0, '2025-11-22'),
(2, 5000.0, '2025-11-20');
