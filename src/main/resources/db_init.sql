create database sample default charset "utf8";
use sample;

create table student
(
	id int unsigned auto_increment,
	stu_number varchar(50) unique not null comment '学号',
	name varchar(50) not null comment '姓名',
	depart varchar(50) not null comment '院系',

	primary key (id)
);