CREATE TABLE TB_USER
(
    id Long NOT NULL,
    name varchar(30) not null,
    email varchar(50) not null,
    birth varchar(8),
    password varchar(255) not null,
    phone varchar(20),
    indate timestamp,
    ldate timestamp,
    primary key(id)
);