--liquibase formatted sql
--changeset liquibase:customer
create table customer
(
    id           serial primary key,
    name         varchar(30),
    surname      varchar(50),
    username     varchar(50),
    password     varchar(50),
    phone_number varchar(30),
    age          int,
    address_id   int not null,
    activation_code varchar(250),
    constraint address_fk foreign key (address_id) references address (id),
    status       varchar(25),
    account_Non_Expired boolean ,
    account_Non_Locked boolean ,
    credentials_Non_Expired boolean ,
    enabled  boolean
);

