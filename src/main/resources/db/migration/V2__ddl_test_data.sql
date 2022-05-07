insert into tbl_employee (id,create_date , update_date , name, phone, salary , surname )
values ((SELECT nextval('tbl_employee_id_seq')),current_date,current_date,'Esen','0705000992', 1, 'Omurchiev');

insert into tbl_employee (id,create_date , update_date , name, phone, salary , surname )
values ((SELECT nextval('tbl_employee_id_seq')),current_date,current_date,'Urmat','0707234312', 1, 'Ulanov');

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values ((SELECT nextval('tbl_product_name_id_seq')), current_date ,current_date ,150,'XZ-3',4);

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values ((SELECT nextval('tbl_product_name_id_seq')), current_date ,current_date ,200,'XM-101',5);

insert into tbl_product_name (id, create_date, update_date, brak_price, product_name , product_price)
values ((SELECT nextval('tbl_product_name_id_seq')), current_date ,current_date ,200,'XG-301',5);

insert into tbl_product_type (id, create_date, update_date , product_type )
values ((SELECT nextval('tbl_product_type_id_seq')),current_date ,current_date ,'черный');

insert into tbl_product_type (id, create_date, update_date , product_type )
values ((SELECT nextval('tbl_product_type_id_seq')),current_date ,current_date ,'коричневый');

insert into tbl_product_type (id, create_date, update_date , product_type )
values ((SELECT nextval('tbl_product_type_id_seq')),current_date ,current_date ,'синий');

insert into tbl_product (id, create_date , update_date , count_brak, count_products , count_saya , count_stanok , id_product_name , id_product_type )
values ((SELECT nextval('tbl_product_id_seq')),current_date ,current_date ,2,3000,1,1,1,1);

insert into tbl_product (id, create_date , update_date , count_brak, count_products , count_saya , count_stanok , id_product_name , id_product_type )
values ((SELECT nextval('tbl_product_id_seq')),current_date ,current_date ,2,2000,1,1,2,1);

insert into tbl_product_employees (products_id , employees_id)
values (1,1);

insert into tbl_product_employees (products_id , employees_id)
values (1,2);

insert into tbl_product_employees (products_id , employees_id)
values (2,2);


create function getsalaryinfo(from_date text, to_date text)
    returns TABLE(id_workers integer, name_workers character varying, product double precision, brak_workers double precision, made_product_currency double precision, made_brak_currency double precision, made_workers_currency double precision)
    language plpgsql
as
$$
BEGIN

    create TEMPORARY table temp_table(id int,
                                      product_name varchar,
                                      product_type varchar,
                                      id_workers int,
                                      name_workers varchar,
                                      product float,
                                      brak_workers float,
                                      made_product_currency float,
                                      made_brak_currency float,
                                      made_workers_currency float,
                                      create_date date);

    insert into temp_table
    select
        p.id "product id",
        (select n.product_name from tbl_product_name n where n.id = p.id_product_name),
        (select t.product_type from tbl_product_type t where t.id = p.id_product_type),
        e.id "id_workers",
        e.name "name_workers",
        p.count_products / (select count(empl.products_id) from tbl_product_employees empl where empl.products_id = p.id group by empl.products_id) "product",
        p.count_brak / (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) "brak_workers",
        (p.count_products / (select count(empl.products_id) from tbl_product_employees empl where empl.products_id = p.id group by empl.products_id) ) * (select n.product_price from tbl_product_name n where n.id = p.id_product_name) "made_product_currency",
        (p.count_brak*(select n.brak_price from tbl_product_name n where n.id = p.id_product_name)) /  (select count(empl.products_id) from tbl_product_employees empl where empl.products_id = p.id group by empl.products_id) "made_brak_currency",
        (p.count_products*(select n.product_price from tbl_product_name n where n.id = p.id_product_name)) /  (select count(empl.products_id) from tbl_product_employees empl where empl.products_id = p.id group by empl.products_id) -
        (p.count_brak*(select n.brak_price from tbl_product_name n where n.id = p.id_product_name)) /  (select count(empl.products_id) from tbl_product_employees empl where empl.products_id = p.id group by empl.products_id) "made_workers_currency",
        p.create_date
    from tbl_product_employees a, tbl_employee e,tbl_product p

    where a.employees_id=e.id and a.products_id = p.id
      and
        p.create_date BETWEEN to_date(from_date,'YYYY-MM-DD') AND to_date(to_date,'YYYY-MM-DD') group by p.id, p.id_product_name , p.id_product_type, e.id order by 1 desc;

    RETURN QUERY SELECT temp.id_workers,
                        temp.name_workers,
                        sum(temp.product) "product",
                        sum(temp.brak_workers) "brak_workers",
                        sum(temp.made_product_currency) "made_product_currency",
                        sum(temp.made_brak_currency) "made_brak_currency",
                        sum(temp.made_workers_currency) "made_workers_currency"
                 FROM temp_table temp group by temp.name_workers, temp.id_workers;
    drop table temp_table;
END;
$$;

alter function getsalaryinfo(text, text) owner to "user";

