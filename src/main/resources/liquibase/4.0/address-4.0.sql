--liquibase formatted sql
--changeset liquibase:address
create table address (
                    id serial primary key ,
                      area varchar(100),
                             city varchar(100),
                             state varchar(100),
                             country varchar(100),
                            pin_code varchar(100) unique,
                            status varchar(25));
