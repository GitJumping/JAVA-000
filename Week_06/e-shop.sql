drop table if exists 'e_user';
create or replace table 'e_user' (
user_id integer primary key comment '用户id，自增主键',
user_name varchar(50) not null comment '用户姓名',
user_nick_name varchar (255) not null comment '用户暱称',
user_account_no varchar (255) not null comment '用户账户，与业务发生关联',
user_passwrod varchar (255) not null comment '用户密码',
create_time timestamp not null default now() comment '用户创建时间',
update_time timestamp not null  default now() comment '用户修改时间'
)engine=innodb default charset=utf8;

drop table if exists 'e_commodity';
create or replace table 'e_commodity'(
commodity_id integer primary key comment '商品id，自增属性',
commodity_key varchar (100) not null comment '商品key，与业务关联',
commodity_name varchar (500) not null comment '商品名称',
commodity_price integer not null default '0' comment '商品价格，以分为单位，乘以100，入表',
commodity_discount double not null default '1.0' comment '商品折扣',
commodity_type varchar (10) not null default 1 comment '商品类别',
commodity_pd date not null comment '商品生成日期',
commodity_desc varchar (500) not null default '' comment '商品的介绍',
create_time timestamp not null default now() comment '商品创建时间',
update_time timestamp not null default now() comment '商品修改时间'
)engine=innodb default charset=utf8;

drop table if exists 'e_order';
create or replace table 'e_order'(
order_id integer comment '订单id',
order_commodity_key varchar (100) not null comment '订单与商品关联',
user_account varchar (255) not null comment '用户账户',
order_status char(2) not null default '0' comment '订单状态',
order_amount integer not null comment '订单商品数量',
order_amount integer not null comment '订单总价格',
order_create_time timestamp not null default now() comment '订单创建的日期',
order_update_time timestamp not null default now() comment '订单修改的日期',
primary key (order_id, user_account, order_commodity_key)
)engine=innodb default charset=utf8;