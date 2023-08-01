--liquibase formatted sql
--changeset liquibase:orders
create table orders (
                    order_id serial primary key ,
                    order_date date,
                    delivery_date date,
                    order_status varchar(25),
                    total_amount decimal,
                    payment_method varchar(25),
                    customer_id int not null,
                    constraint customers_fk foreign key (customer_id) references customer (id),
                           accept_status varchar(25));


