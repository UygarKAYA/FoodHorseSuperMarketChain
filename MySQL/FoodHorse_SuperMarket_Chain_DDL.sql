create table customer
(
    customerID int AUTO_INCREMENT not null, 
    customerName varchar(20), 
    customerSurname varchar(20),
    customerAdress varchar(40),
    customerPhoneNumber char(11), 
    primary key (customerID)
);

create table branches
(
    branchID int AUTO_INCREMENT not null,
    branchName varchar(20), 
    branchAdress varchar(40),
    primary key (branchID)
);

create table product
(
    productID int AUTO_INCREMENT not null,
    productName varchar(40),
    productDescription varchar(70),
    productPrice double, CHECK (productPrice >=0),
    primary key (productID)
);

create table orderTime
(
    customerID int,
    branchID int,
    productID int, 
    Otime varchar(20) not null,
    primary key (Otime),
    foreign key (productID) references product(productID),
    foreign key (branchID) references branches(branchID),
    foreign key (customerID) references customer(customerID)
);

create table stock
(
    branchID int,
    productID int,
    stockQuantity int not null, CHECK (stockQuantity >= 0),
    primary key (productID, branchID),
    foreign key (productID) references product(productID),
    foreign key (branchID) references branches(branchID)
);
