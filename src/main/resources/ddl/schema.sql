CREATE TABLE TB_USER
(
    id int NOT NULL,
    name varchar(30) not null,
    email varchar(50) not null,
    birth varchar(8),
    password varchar(255) not null,
    phone varchar(20),
    created_at timestamp,
    updated_at timestamp,
    primary key(id)
);