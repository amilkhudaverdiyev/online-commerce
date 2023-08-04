--liquibase formatted sql
--changeset liquibase:order_detail
create table order_detail
(
    id          serial primary key,
    product_id  int not null,
    constraint product_fk foreign key (product_id) references products (product_id),
    price       decimal,
    quantity    int,
    total_price decimal,
    order_id    int not null,
    constraint track_fk foreign key (order_id) references orders (order_id)
);


