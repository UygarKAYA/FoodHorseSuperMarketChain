insert into customer (customerName, customerSurname, customerAdress, customerPhoneNumber) values ('Seçkin', 'Ağırbaş', 'Alibeyköy', '05001112233');
insert into customer (customerName, customerSurname, customerAdress, customerPhoneNumber) values ('Mahmut', 'Acar', 'Kartal', '05002221133');
insert into customer (customerName, customerSurname, customerAdress, customerPhoneNumber) values ('Merve', 'Özkan', 'Beşiktaş', '05003332211');
insert into customer (customerName, customerSurname, customerAdress, customerPhoneNumber) values ('Aysel', 'Tekin', 'Kadıköy', '05002223311');
insert into customer (customerName, customerSurname, customerAdress, customerPhoneNumber) values ('Orhan', 'Yavuz', 'Ataşehir', '05003331122');

insert into branches (branchName, branchAdress) values ('Alibeyköy Center', 'Alibeyköy');
insert into branches (branchName, branchAdress) values ('Kadıköy Center', 'Kadıköy');
insert into branches (branchName, branchAdress) values ('Kadıköy Pier', 'Kadıköy');
insert into branches (branchName, branchAdress) values ('Beşiktaş Square', 'Beşiktaş');
insert into branches (branchName, branchAdress) values ('Taksim Square', 'Taksim');

insert into product (productName, productDescription, productPrice) values ('FoodHorse Olive Oil', 'FoodHorse brand olive oil 1L', 11.50); 
insert into product (productName, productDescription, productPrice) values ('FoodHorse Rice', 'FoodHorse brand rice 2.5kg', 13.75);
insert into product (productName, productDescription, productPrice) values ('FoodHorse Milk', 'FoodHorse brand whole milk 1L', 3.75);
insert into product (productName, productDescription, productPrice) values ('FoodHorse Kosher Dill Pickles', 'FoodHorse brand pickles 680g', 4.45);
insert into product (productName, productDescription, productPrice) values ('FoodHorse Strawberry Jam', 'FoodHorse Strawberry Jam 380g', 6.45);

insert into orderTime(customerID, branchID, productID, Otime) values (1,2,4,'2017-02-09 03:54:12');
insert into orderTime(customerID, branchID, productID, Otime) values (1,1,1,'2010-12-11 09:45:17'); 
insert into orderTime(customerID, branchID, productID, Otime) values (2,1,1,'2012-12-12 10:32:43');
insert into orderTime(customerID, branchID, productID, Otime) values (1,2,1,'2013-11-25 03:54:35');
insert into orderTime(customerID, branchID, productID, Otime) values (3,2,2,'2014-03-04 10:23:21');
insert into orderTime(customerID, branchID, productID, Otime) values (1,4,1,'2020-12-12 12:12:12');
insert into orderTime(customerID, branchID, productID, Otime) values (5,2,1,'2015-11-08 14:44:52');
insert into orderTime(customerID, branchID, productID, Otime) values (1,1,3,'2016-12-12 10:32:45');
insert into orderTime(customerID, branchID, productID, Otime) values (3,2,1,'2018-06-04 10:23:29');
insert into orderTime(customerID, branchID, productID, Otime) values (1,3,1,'2019-11-11 11:11:44');

insert into stock (branchID ,productID, stockQuantity) values (1,1,100);
insert into stock (branchID ,productID, stockQuantity) values (2,3,200);
insert into stock (branchID ,productID, stockQuantity) values (3,1,500);
insert into stock (branchID ,productID, stockQuantity) values (4,3,3000);