--liquibase formatted sql
--changeset liquibase:discount
create table discount (
                    id serial primary key ,
                             discount decimal,
                             discount_date timestamp,
                             end_date timestamp,
                    product_id int not null,
                    constraint product_fk foreign key (product_id) references products (product_id),
                            status varchar(25));