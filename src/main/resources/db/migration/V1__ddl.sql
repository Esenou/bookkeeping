 create table tbl_employee (id  bigserial not null, create_date timestamp not null, update_date timestamp, name varchar(255), phone varchar(255), salary float8, surname varchar(255), primary key (id));
     create table tbl_product (id  bigserial not null, create_date timestamp not null, update_date timestamp, count_brak float8, count_products float8 not null, count_saya float8, count_stanok float8, id_product_name int8 not null, id_product_type int8 not null, primary key (id));
     create table tbl_product_employees (products_id int8 not null, employees_id int8 not null, primary key (products_id, employees_id));
     create table tbl_product_name (id  bigserial not null, create_date timestamp not null, update_date timestamp, brak_price float8 not null, product_name varchar(255) not null, product_price float8 not null, primary key (id));
     create table tbl_product_type (id  bigserial not null, create_date timestamp not null, update_date timestamp, product_type varchar(255) not null, primary key (id));
     alter table tbl_product add constraint FKp0youvfe87h4w39ixts92cuti foreign key (id_product_name) references tbl_product_name;
     alter table tbl_product add constraint FKrd68se3hnfsfactdugl6bmld4 foreign key (id_product_type) references tbl_product_type;
     alter table tbl_product_employees add constraint FK7n6qipcx3oftofa9fd3lgvbtl foreign key (employees_id) references tbl_employee;
     alter table tbl_product_employees add constraint FKske3vtfcb1ooj8q1vh361uy21 foreign key (products_id) references tbl_product;