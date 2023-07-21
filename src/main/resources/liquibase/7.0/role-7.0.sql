--liquibase formatted sql
--changeset liquibase:role
create table role (
                          id serial primary key ,
                          authority varchar(30),
                          status varchar(25));