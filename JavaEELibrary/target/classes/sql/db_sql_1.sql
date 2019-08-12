create database if not exists library
character set utf8;
use library;

create table if not exists book
(
    id INT auto_increment,
    name varchar(255) not null unique,
    authorName varchar(255) not null,
    pages int not null,
    resourcePath varchar(255) not null,
    primary key (id)
) engine = innodb;

create table if not exists user
(
    id int auto_increment,
    login varchar(255) not null unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(6) not null,
    primary key (id)
) engine = innodb;

create table if not exists issuedbook
(
    id int auto_increment,
    userid int not null,
    bookid int not null,
    primary key (id),
    unique (userid, bookid),

    constraint userid
    foreign key userid(userid)
    references user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    CONSTRAINT bookid
    foreign key bookid(bookid)
    references book(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) engine = innodb;



create table if not exists testuser
(
    id int auto_increment,
    login varchar(255) not null unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(6) not null,
    primary key (id)
) engine = innodb;


create table if not exists testbook
(
    id INT auto_increment,
    name varchar(255) not null unique,
    authorName varchar(255) not null,
    pages int not null,
    resourcePath varchar(255) not null,
    primary key (id)
) engine = innodb;