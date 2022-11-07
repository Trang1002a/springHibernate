use master
go
create database C2103LM_BTL
go
use C2103LM_BTL
go
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
	product_name nvarchar(50),
	quantity int,
	status int default(0) 
)
go
INsert into tbl_category(name, status) Values
( N'IPHONE 6', 1)
go
insert into tbl_product(name,price,sale_price,image, category_id) VALUES
(N'Iphone 6 Plus', 6500000, 0, '', 1)