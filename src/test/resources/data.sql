CREATE TABLE TB_USER
(
    id long NOT NULL,
    name varchar(30) not null,
    email varchar(50) not null,
    birth varchar(8),
    password varchar(255) not null,
    phone varchar(20),
    indate timestamp,
    ldate timestamp,
    primary key(id)
);

insert into TB_USER (`id`, `name`, `email`, `birth`, `password`, `phone`, `indate`, `ldate`) values (1, 'reo1', 'reo1@gmail.com', '20200101', '12345678', '01011112222' ,now(), now());

insert into TB_USER (`id`, `name`, `email`, `birth`, `password`, `phone`, `indate`, `ldate`) values (1, 'reo1', 'reo1@gmail.com', '20200101', '12345678', '01011112222' ,now(), now());

insert into TB_USER (`id`, `name`, `email`, `birth`, `password`, `phone`, `indate`, `ldate`) values (1, 'reo1', 'reo1@gmail.com', '20200101', '12345678', '01011112222' ,now(), now());

insert into TB_USER (`id`, `name`, `email`, `birth`, `password`, `phone`, `indate`, `ldate`) values (1, 'reo1', 'reo1@gmail.com', '20200101', '12345678', '01011112222' ,now(), now());

insert into TB_USER (`id`, `name`, `email`, `birth`, `password`, `phone`, `indate`, `ldate`) values (1, 'reo1', 'reo1@gmail.com', '20200101', '12345678', '01011112222' ,now(), now());

