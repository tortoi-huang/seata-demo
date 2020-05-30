CREATE DATABASE account;
CREATE DATABASE stock;
CREATE DATABASE score;

use account;

create table account
(
    id bigint not null auto_increment comment '主键自增',
    account bigint default 0 not null comment '账户金额',
    constraint n_account_pk
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into account(id,account) values (1,1000);
insert into account(id,account) values (9,1000);

use stock;

create table stock
(
    id bigint not null auto_increment comment '主键自增',
    stock bigint default 0 not null comment '库存数量',
    constraint n_stock_pk
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into stock(id,stock) values (1,20000);
insert into stock(id,stock) values (9,20000);


use score;

create table score
(
    id bigint not null auto_increment comment '主键自增',
    score bigint default 0 not null comment '积分数量',
    constraint n_score_pk
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into score(id,score) values (1,300000);
insert into score(id,score) values (9,300000);