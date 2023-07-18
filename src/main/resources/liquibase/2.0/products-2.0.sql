--liquibase formatted sql
--changeset liquibase:products
create table products (
                        product_id serial primary key ,
                            name varchar(25) unique,
                            description varchar(255),
                            current_quantity int,
                            unit_price decimal,
                        category_id  int not null,
                        constraint category_fk foreign key (category_id) references categories (category_id),
                            status varchar(25));