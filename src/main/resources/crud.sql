create table admin
(
    admin_id    int auto_increment
        primary key,
    user_id     int      not null,
    create_time datetime null,
    update_time datetime null
);

create table user
(
    user_id   int auto_increment
        primary key,
    name      varchar(20)          null,
    age       int                  null,
    email     varchar(20)          null,
    gender    char                 null,
    avatar    varchar(50)          null,
    detail    text                 null,
    level     tinyint    default 0 null,
    adminCode tinyint(1) default 0 not null,
    tel       varchar(20)          null,
    birth     datetime             null
);

create table user_login
(
    user_id  int         not null
        primary key,
    username varchar(20) null,
    password varchar(20) null
);


