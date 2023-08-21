--liquibase formatted sql
--changeset liquibase:payment
create table payment (
                            id  varchar(256)  primary key ,
                            payment decimal,
                            sender_id    varchar(256) not null,
                            constraint sender_fk foreign key (sender_id) references account (account_id),
                                receiver_id    varchar(256) not null,
                            constraint receiver_fk foreign key (receiver_id) references account (account_id),
                            date timestamp,
                            status varchar(15)
                            );