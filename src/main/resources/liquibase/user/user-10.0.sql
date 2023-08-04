--liquibase formatted sql
--changeset liquibase:users
create table user_detail (
                       id serial primary key ,
                       username varchar(25) not null ,
                       password  varchar(25) not null ,
                       account_Non_Expired boolean ,
                       account_Non_Locked boolean ,
                       credentials_Non_Expired boolean ,
                       enabled  boolean
                    );