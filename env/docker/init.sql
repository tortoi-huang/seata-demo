CREATE DATABASE account;
CREATE DATABASE stock;

use account;

create table account
(
    id bigint not null auto_increment comment '主键自增',
    account bigint default 0 not null comment '账户金额',
    constraint n_account_pk
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into account(id,account) values (1,1000);

use stock;

create table stock
(
    id bigint not null auto_increment comment '主键自增',
    stock bigint default 0 not null comment '库存数量',
    constraint n_account_pk
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into stock(id,stock) values (1,20000);