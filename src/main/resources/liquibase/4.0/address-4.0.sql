--liquibase formatted sql
--changeset liquibase:address
create table address
(
    id       serial primary key,
    country  varchar(100),
    city     varchar(100),
    district    varchar(100),
    street  varchar(100),
    apartment_number varchar(100),
    status   varchar(25)
);
