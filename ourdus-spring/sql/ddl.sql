create table user(
    seq bigint not null,
    email varchar(20) not null,
    pw varchar(20) not null,
    name varchar(20) not null,
    tel varchar(20) not null,
    primary key(email)
);