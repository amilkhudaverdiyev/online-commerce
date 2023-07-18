--liquibase formatted sql
--changeset liquibase:customer
create table customer (
                    id serial primary key ,
                             name varchar(30),
                             surname varchar(50),
                             email varchar(50) unique,
                             age int,
                             phone_number varchar(30) unique ,
                             address_id int not null,
                    constraint address_fk foreign key (address_id) references address (id),
                            status varchar(25));

