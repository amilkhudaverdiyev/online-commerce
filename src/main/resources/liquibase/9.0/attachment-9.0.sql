--liquibase formatted sql
--changeset liquibase:attachment
create table attachment (
                            id serial primary key ,
                            file_name varchar(100),
                            file_type varchar(15),
                            data bytea);