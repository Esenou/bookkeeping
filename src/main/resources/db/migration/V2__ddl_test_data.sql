insert into tbl_employee (id,create_date , update_date , name, phone, salary , surname )
values (1,current_date,current_date,'Esen','0705000992', 1, 'Omurchiev');

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values (1, current_date ,current_date ,200,'XXL',5);

insert into tbl_product_type (id, create_date, update_date , product_type )
values (1,current_date ,current_date ,'GREEN');

insert into tbl_product (id, create_date , update_date , count_brak, count_products , count_saya , count_stanok , id_product_name , id_product_type )

values (1,current_date ,current_date ,2,3000,1,1,1,1);

insert into tbl_product_employees (products_id , employees_id)
values (1,1);


