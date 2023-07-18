--liquibase formatted sql
--changeset liquibase:cart_item
create table cart_item (
                    id serial primary key ,
                             customer_id int not null,
                    constraint customer_fk foreign key (customer_id) references customer (id),
                    product_id int not null,
                    constraint product_fk foreign key (product_id) references products (product_id),
                    quantity int,
                    price decimal,
                    total_price decimal,
                           status varchar(25));

