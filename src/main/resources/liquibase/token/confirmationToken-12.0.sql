--liquibase formatted sql
--changeset liquibase:confirmation_token
create table confirmation_token  (
                            id serial primary key ,
                            token varchar(100),
                            created_at timestamp,
                            expires_at timestamp,
                            confirmed_at timestamp,
                            user_id   int not null,
                            constraint role_fk foreign key (user_id) references customer (id));
