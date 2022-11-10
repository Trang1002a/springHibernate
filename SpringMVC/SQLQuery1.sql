use master
go
create database C2103LM_BTL
go
use C2103LM_BTL
go
drop table tbl_product
go
drop table tbl_category
go
drop table tbl_warehouse
create table tbl_category
(
    id int primary key identity,
	name nvarchar(50),
    status int default(0),
    created_date datetime DEFAULT(getdate())
)
    go
create table tbl_product
(
    id int primary key identity,
    name nvarchar(50),
	status int default(0),
    price float default(0) ,
    sale_price float default(0),
    image varchar(200),
    created_date datetime DEFAULT(getdate()),
    category_id int FOREIGN KEY REFERENCES tbl_category(id)
)
go
create table tbl_warehouse
(
	id int primary key identity,
	product_id int FOREIGN KEY REFERENCES tbl_product(id),
	quantity int,
	status int default(0),
	price float default(0),
	product_name nvarchar(50)
)
go
INsert into tbl_category(name, status) Values
( N'IPHONE 6', 0),
( N'IPHONE 7', 1),
( N'IPHONE 8', 1),
( N'IPHONE X', 1),
( N'IPHONE 11', 1),
( N'IPHONE 12', 1),
( N'IPHONE 13', 1),
( N'IPHONE 14', 1)
go
insert into tbl_product(name,status,price,sale_price,image, category_id) VALUES
(N'Iphone 6 Plus', 1, 6500000, 0, '', 1),
(N'Iphone 7', 1, 7000000, 0, 'iphone-7.jpg', 2),
(N'Iphone 7 Plus', 1, 7500000, 0, 'iphone-7.jpg', 2),
(N'Iphone 8', 1, 8000000, 0, 'iphone-8.jpg', 3),
(N'Iphone 8 Plus', 1, 8500000, 0, 'iphone-8-plus.jpg', 3),
(N'Iphone X', 1, 9000000, 0, 'iphone-x.jpg', 4),
(N'Iphone Xr', 1, 95000000, 0, '', 4),
(N'Iphone Xs', 1, 9500000, 0, 'iphone-xs.jpg', 4),
(N'Iphone XSMax', 1, 1000000, 0, 'iphone-xs-max.jpg', 4),
(N'Iphone 11', 1, 11000000, 0, 'iphone-11.jpg', 5),
(N'Iphone 11 Pro', 1, 12000000, 0, '.jpg', 5),
(N'Iphone 11 ProMax', 1, 13000000, 0, 'iphone-11-pro-max.jpg', 5),
(N'Iphone 12', 1, 14000000, 0, 'iphone-12.jpg', 6),
(N'Iphone 12 Pro', 1, 15000000, 0, '', 6),
(N'Iphone 12 ProMax', 1, 16000000, 0, 'iphone-12-pro-max.jpg', 6),
(N'Iphone 13', 1, 16000000, 0, 'iphone-13.jpg', 7),
(N'Iphone 13 Pro', 1, 17000000, 0, '', 7),
(N'Iphone 13 ProMax', 1, 18000000, 0, 'iphone-13-pro-max.jpg', 7),
(N'Iphone 14', 1, 19000000, 0, '', 8),
(N'Iphone 14 Plus', 1, 20000000, 0, '', 8),
(N'Iphone 14 Pro', 1, 21000000, 0, 'iphone-14-pro.jpg', 8),
(N'Iphone 14 ProMax', 1, 22000000, 0, 'iphone-14-pro-max.jpg', 8)