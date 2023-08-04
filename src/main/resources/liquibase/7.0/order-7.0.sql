--liquibase formatted sql
--changeset liquibase:orders
create table orders (
                    order_id serial primary key ,
                    order_date date,
                    delivery_date date,
                    total_amount decimal,
                    customer_id int not null,
                    constraint customers_fk foreign key (customer_id) references customer (id),
                           status varchar(25));


