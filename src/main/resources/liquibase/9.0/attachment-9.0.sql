--liquibase formatted sql
--changeset liquibase:attachment
create table attachment (
                            id serial primary key ,
                            file_name varchar(100),
                            file_type varchar(15),
                            data bytea,
                            customer_id int not null,
                            constraint customers_fk foreign key (customer_id) references customer (id));