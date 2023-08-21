--liquibase formatted sql
--changeset liquibase:account
create table account (
                            account_id  varchar(256)  primary key ,
                            name varchar(30),
                            amount decimal,
                            card_number varchar(16),
                            expiry_date date,
                            status varchar(15),
                            customer_id    int not null,
                            constraint customer_fk foreign key (customer_id) references customer (id));
