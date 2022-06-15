
INSERT INTO public.tbl_employee (id, create_date, update_date, name, phone, salary, surname) VALUES ((SELECT nextval('tbl_employee_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 'Esen', '0705000992', 1, 'Omurchiev');
INSERT INTO public.tbl_employee (id, create_date, update_date, name, phone, salary, surname) VALUES ((SELECT nextval('tbl_employee_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 'Urmat', '0707234312', 1, 'Ulanov');

INSERT INTO public.tbl_product_name (id, create_date, update_date, brak_price, product_name, product_price) VALUES ((SELECT nextval('tbl_product_name_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 150, 'XZ-3', 4);
INSERT INTO public.tbl_product_name (id, create_date, update_date, brak_price, product_name, product_price) VALUES ((SELECT nextval('tbl_product_name_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 200, 'XM-101', 5);
INSERT INTO public.tbl_product_name (id, create_date, update_date, brak_price, product_name, product_price) VALUES ((SELECT nextval('tbl_product_name_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 200, 'XG-301', 5);


INSERT INTO public.tbl_product_type (id, create_date, update_date, product_type) VALUES ((SELECT nextval('tbl_product_type_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 'черный');
INSERT INTO public.tbl_product_type (id, create_date, update_date, product_type) VALUES ((SELECT nextval('tbl_product_type_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 'коричневый');
INSERT INTO public.tbl_product_type (id, create_date, update_date, product_type) VALUES ((SELECT nextval('tbl_product_type_id_seq')), '2022-05-08 00:00:00.000000', '2022-05-08 00:00:00.000000', 'синий');

INSERT INTO public.tbl_product (id, create_date, update_date, count_brak, count_products, count_saya, count_stanok, in_bags, packaging,create_date_product, id_product_name, id_product_type) VALUES ((SELECT nextval('tbl_product_id_seq')), '2022-06-05 11:21:12.000000', '2022-06-05 11:22:57.000000', 2, 3000, 1, 1, 1, 1, '05-05-2022',1, 1);
INSERT INTO public.tbl_product (id, create_date, update_date, count_brak, count_products, count_saya, count_stanok, in_bags, packaging,create_date_product, id_product_name, id_product_type) VALUES ((SELECT nextval('tbl_product_id_seq')), '2022-06-05 11:21:12.000000', '2022-06-05 11:22:57.000000', 2, 3000, 1, 1, 1, 1, '05-05-2022',2, 1);
INSERT INTO public.tbl_product (id, create_date, update_date, count_brak, count_products, count_saya, count_stanok, in_bags, packaging,create_date_product, id_product_name, id_product_type) VALUES ((SELECT nextval('tbl_product_id_seq')), '2022-06-05 11:21:12.000000', '2022-06-05 11:22:57.000000', 1, 3000, 1, 1, 1, 1, '05-05-2022',1, 1);

INSERT INTO public.tbl_user (id, create_date, update_date, password, username) VALUES ((SELECT nextval('tbl_user_id_seq')), '2022-05-09 01:38:02.000000', '2022-05-09 01:38:04.000000', '$2a$08$L0moD6DrrZM9PFdcDd0GQOkOuaO4GmNSpZBJ8UJRrTT7KhN1ybgpS', 'admin');


INSERT INTO public.tbl_product_employees (products_id, employees_id) VALUES (1, 1);
INSERT INTO public.tbl_product_employees (products_id, employees_id) VALUES (1, 2);
INSERT INTO public.tbl_product_employees (products_id, employees_id) VALUES (2, 2);
INSERT INTO public.tbl_product_employees (products_id, employees_id) VALUES (3, 1);

INSERT INTO public.user_roles (user_id, roles) VALUES (1, 0);


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

alter function getsalaryinfo(text, text) owner to "postgres";

create or replace function getemployee(from_date text, to_date text)
    returns TABLE(surname character varying, name character varying)
    language plpgsql
as
$$
BEGIN
RETURN QUERY  select emp.surname,emp.name from tbl_product prod inner join tbl_product_employees prod_emp
                                                                     on prod.id = prod_emp.products_id
                                                          inner join tbl_employee  emp
                                                                     on emp.id = prod_emp.employees_id
        where prod.create_date_product BETWEEN to_date(from_date,'YYYY-MM-DD') AND to_date(to_date,'YYYY-MM-DD')
        group by emp.name, emp.surname;
END;
$$;

alter function getemployee(text, text) owner to "postgres";

create function getsalary(from_date text, to_date text)
    returns TABLE(surname character varying, name character varying,salary double precision, create_date date )
    language plpgsql
as
$$
BEGIN
    create TEMPORARY table product(
                                      count_product float,
                                      create_date_product date);

WITH productCount
         as (
        select
            p.id,
            (select product_name from tbl_product_name prod where prod.id = p.id_product_name),
            (select product_type from tbl_product_type prod where prod.id = p.id_product_type),
            SUM(p.packaging) "packaging",
            SUM(p.count_products) "product",
            SUM(p.in_bags) "in bags",
            SUM(p.count_brak) "brak workers",
            SUM(p.count_stanok) "brak stanok",
            SUM(p.count_saya) "brak say",
            SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) "made_product_currency",
            SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) "made_brak_currency",
            SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) - SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) "made_workers_currency",
            p.create_date_product
        from tbl_product p where p.create_date_product BETWEEN to_date(from_date,'YYYY-MM-DD') AND to_date(to_date,'YYYY-MM-DD')
        group by id_product_name , id_product_type, p.id)
insert into product select
                        sum(made_workers_currency),
                        create_date_product
                    from productCount
                    group by create_date_product;

create TEMPORARY table employee(
                                       count_employee float,
                                       create_date_product date);
WITH employeeCount
         AS(
        select emp.id, prod.create_date_product from tbl_product prod inner join tbl_product_employees prod_emp
                                                                                 on prod.id = prod_emp.products_id
                                                                      inner join tbl_employee  emp
                                                                                 on emp.id = prod_emp.employees_id
        where prod.create_date_product BETWEEN to_date(from_date,'YYYY-MM-DD') AND to_date(to_date,'YYYY-MM-DD')
        group by
            emp.id,prod.create_date_product)
insert into employee SELECT  count(create_date_product) as count_product , create_date_product  from employeeCount
                     group by create_date_product;


create TEMPORARY table salary(salary float,
                                  create_date date);

insert into  salary select count_product/e.count_employee as salary,
                           e.create_date_product as create_date
from product inner join employee e on product.create_date_product = e.create_date_product;




RETURN QUERY  select emp.surname,emp.name,s.salary, prod.create_date_product from tbl_product prod inner join tbl_product_employees prod_emp
                                                                                                        on prod.id = prod_emp.products_id
                                                                                             inner join tbl_employee  emp
                                                                                                        on emp.id = prod_emp.employees_id
                                                                             inner join salary s on prod.create_date_product = s.create_date
                  where prod.create_date_product BETWEEN to_date(from_date,'YYYY-MM-DD') AND to_date(to_date,'YYYY-MM-DD')
                  group by s.salary, emp.name, emp.surname, prod.create_date_product;

drop table product;
drop table employee;
drop table salary;
END;
$$;

alter function getsalary(text, text) owner to postgres;


