--liquibase formatted sql
--changeset liquibase:category
create table categories (
                           category_id serial primary key ,
                           name varchar(25) unique,
                           status varchar(25));