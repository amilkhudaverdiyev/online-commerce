--liquibase formatted sql
--changeset liquibase:customer
create table customer (
                    id serial primary key ,
                             name varchar(30),
                             surname varchar(50),
                             username varchar(50) ,
                             password varchar(50) ,
                             phone_number varchar(30)  ,
                             age int,
                             address_id int not null,
                    constraint address_fk foreign key (address_id) references address (id),
                   role varchar(25),
                            status varchar(25));

