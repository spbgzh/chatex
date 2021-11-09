drop database if exists my;

create database my;

use my;

create table hobby_scores
(
	id int auto_increment,
	sports int default 0 not null,
	music int default 0 not null,
	travel int default 0 not null,
	reading int default 0 not null,
	art int default 0 not null,
	movie int default 0 not null,
	cartoon int default 0 not null,
	games int default 0 not null,
	cooking int default 0 not null,
	shopping int default 0 not null,
	constraint hobby_scores_pk
		primary key (id)
);

create table user
(
	id int auto_increment,
	age varchar(5) not null,
	username varchar(30) not null,
	password varchar(30) not null,
	gender varchar(5) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null,
	phone_number varchar(30) not null,
	role varchar(5) not null,
	constraint user_pk
		primary key (id)
);

create table user_introduction
(
	id int auto_increment,
	aboutMe varchar(100) default 'This user left nothing' not null,
	somethingToSay varchar(100) default 'This user left nothing' not null,
	constraint user_pk
		primary key (id)
);

create table user_like_dislike
(
	id int auto_increment,
	like int default 0 not null,
	dislike int default 0 not null,
	constraint user_like_disike_pk
		primary key (id)
);








