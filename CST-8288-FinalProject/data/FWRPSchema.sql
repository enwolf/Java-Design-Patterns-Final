/**
* Author: Robin Phillis
* Date 03-21-2024
*/

DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

-- Create User Table
CREATE TABLE user (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VarChar(255) NOT NULL,
    Email UNIQUE VARCHAR(255)  NOT NULL,
    Password VARCHAR(255) NOT NULL,
    UserType ENUM('RETAILER', 'CONSUMER', 'CHARITABLE_ORGANIZATION') NOT NULL
);

-- Create Consumer Table
CREATE TABLE consumer (
    ConsumerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20) NOT NULL,
    StreetAddress VARCHAR(255) NOT NULL,
    City VARCHAR(100) NOT NULL,
    Province VARCHAR(100) NOT NULL,
    PostalCode VARCHAR(20) NOT NULL,
    AccountBalance DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (UserID) REFERENCES user(UserID)
);

-- Create Retailer Table
CREATE TABLE retailer (
    RetailerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE NOT NULL,
    StoreName VARCHAR(255) NOT NULL,
    StreetAddress VARCHAR(255) NOT NULL,
    City VARCHAR(100) NOT NULL,
    Province VARCHAR(100) NOT NULL,
    PostalCode VARCHAR(20) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES user(UserID)
);

-- Create CharitableOrganization Table
CREATE TABLE charitableOrganization (
    OrganizationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE NOT NULL,
    OrganizationName VARCHAR(255) NOT NULL,
    StreetAddress VARCHAR(255) NOT NULL,
    City VARCHAR(100) NOT NULL,
    Province VARCHAR(100) NOT NULL,
    PostalCode VARCHAR(20) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES user(UserID)
);

-- Create Inventory Table
CREATE TABLE inventory (
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,
    RetailerID INT NOT NULL,
    ItemName VARCHAR(255) NOT NULL,
    Quantity INT NOT NULL,
    ExpirationDate DATE NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    DiscountRate DECIMAL(6, 2) DEFAULT 0.00,
    DiscountAmount DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (RetailerID) REFERENCES Retailer(RetailerID)
);

-- Create Surplus Food Table
CREATE TABLE surplusFood (
    SurplusFoodID INT AUTO_INCREMENT PRIMARY KEY,
    InventoryID INT NOT NULL,
    Status ENUM('Available', 'Claimed', 'Sold') NOT NULL,
    DiscountRate DECIMAL(6, 2) DEFAULT 0.00,
    DiscountAmount DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (InventoryID) REFERENCES inventory(InventoryID)
);

-- Create Transactions Table
CREATE TABLE transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    ConsumerID INT NOT NULL,
    SurplusFoodID INT NOT NULL,
    PurchaseDate DATETIME NOT NULL,
    AmountPaid DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ConsumerID) REFERENCES Consumer(ConsumerID),
    FOREIGN KEY (SurplusFoodID) REFERENCES surplusFood(SurplusFoodID)
);

-- Create Claim Table
CREATE TABLE claim (
    ClaimID INT AUTO_INCREMENT PRIMARY KEY,
    OrganizationID INT NOT NULL,
    SurplusFoodID INT NOT NULL,
    ClaimDate DATETIME NOT NULL,
    FOREIGN KEY (OrganizationID) REFERENCES CharitableOrganization(OrganizationID),
    FOREIGN KEY (SurplusFoodID) REFERENCES surplusFood(SurplusFoodID)
);

-- Create Subscription Table
CREATE TABLE subscription (
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT UNIQUE NOT NULL,
    ContactMethod ENUM('Email', 'Phone') NOT NULL,
    ContactInformation VARCHAR(255) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES user(UserID)
);
