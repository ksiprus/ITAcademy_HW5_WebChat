CREATE SCHEMA IF NOT EXISTS webchat
    AUTHORIZATION postgres;
create table webchat.users
(
    id         serial
        primary key,
    login      varchar(50)  not null
        unique,
    password   varchar(255) not null,
    name       varchar(100) not null,
    birth_date date,
    reg_date   timestamp    not null,
    role       varchar(10)  not null
);

alter table users
    owner to postgres;

INSERT INTO users (login, password, name, birth_date, reg_date, role)
VALUES ('ksiprus', '1234', 'Владимир Шуневич', '1988-11-21', NOW(), 'Admin');



create table webchat.messages
(
    id          serial
        primary key,
    sender_id   integer                             not null
        references webchat.users
            on delete cascade,
    receiver_id integer                             not null
        references webchat.users
            on delete cascade,
    text        text                                not null,
    sent_at     timestamp default CURRENT_TIMESTAMP not null
);



