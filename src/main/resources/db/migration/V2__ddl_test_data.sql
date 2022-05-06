insert into tbl_employee (id,create_date , update_date , name, phone, salary , surname )
values (1,current_date,current_date,'Esen','0705000992', 1, 'Omurchiev');

insert into tbl_employee (id,create_date , update_date , name, phone, salary , surname )
values (2,current_date,current_date,'Urmat','0707234312', 1, 'Ulanov');

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values (1, current_date ,current_date ,150,'XZ-3',4);

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values (2, current_date ,current_date ,200,'XM-101',5);

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values (3, current_date ,current_date ,200,'XG-301',5);

insert into tbl_product_type (id, create_date, update_date , product_type )
values (1,current_date ,current_date ,'черный');

insert into tbl_product_type (id, create_date, update_date , product_type )
values (2,current_date ,current_date ,'коричневый');

insert into tbl_product_type (id, create_date, update_date , product_type )
values (3,current_date ,current_date ,'синий');

insert into tbl_product (id, create_date , update_date , count_brak, count_products , count_saya , count_stanok , id_product_name , id_product_type )

values (1,current_date ,current_date ,2,3000,1,1,1,1);

insert into tbl_product_employees (products_id , employees_id)
values (1,1);


insert into tbl_product_employees (products_id , employees_id)
values (1,2);


insert into tbl_product (id, create_date , update_date , count_brak, count_products , count_saya , count_stanok , id_product_name , id_product_type )
values (2,current_date ,current_date ,2,2000,1,1,2,1);

insert into tbl_product_employees (products_id , employees_id)
values (2,2);
