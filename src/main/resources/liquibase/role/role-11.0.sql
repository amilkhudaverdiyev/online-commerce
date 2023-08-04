--liquibase formatted sql
--changeset liquibase:user_role
create table roles (
                           id           serial primary key,
                       name varchar(25),
                           user_id   int not null,
                           constraint role_fk foreign key (user_id) references customer (id));
