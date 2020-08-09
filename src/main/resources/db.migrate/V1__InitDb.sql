drop table if exists passengers;
drop table if exists roles;
drop table if exists route_station;
drop table if exists routes;
drop table if exists schedule;
drop table if exists stations;
drop table if exists stations_for_trains;
drop table if exists tickets;
drop table if exists trains;

create table passengers
(
    id         integer      not null auto_increment,
    active     bit          not null,
    birth_date date         not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    username   varchar(255) not null,
    primary key (id)
) engine = MyISAM;

create table roles
(
    passenger_id integer not null,
    roles        varchar(255)
) engine = MyISAM;

create table route_station
(
    route_id   integer not null,
    station_id integer not null,
    primary key (route_id, station_id)
) engine = MyISAM;

create table routes
(
    id integer not null auto_increment,
    primary key (id)
) engine = MyISAM;

create table schedule
(
    id             integer not null auto_increment,
    departure_time time,
    station_id     integer not null,
    train_id       integer not null,
    primary key (id)
) engine = MyISAM;

create table stations
(
    id   integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = MyISAM;

create table stations_for_trains
(
    id         integer not null,
    station_id integer not null auto_increment,
    train_id   integer not null,
    primary key (station_id, train_id)
) engine = MyISAM;

create table tickets
(
    id            integer not null,
    departureTime time,
    passenger_id  integer not null,
    route_id      integer not null,
    train_id      integer not null auto_increment,
    primary key (train_id, passenger_id)
) engine = MyISAM;

create table trains
(
    id           integer      not null auto_increment,
    seats_amount integer      not null,
    number       varchar(255) not null,
    primary key (id)
) engine = MyISAM;

alter table passengers
    add constraint UK_nplwv2tutkduhyiu0sf5w8ktk unique (username);
alter table trains
    add constraint UK_fl8n21arxdvb1hh98o4mn9csm unique (number);
alter table roles
    add constraint FK18hjovci05nat3op3efrj9b4o foreign key (passenger_id) references passengers (id);
alter table route_station
    add constraint FKpuv5a902k88lm6iqytlmnp2p4 foreign key (station_id) references stations (id);
alter table route_station
    add constraint FKklcly683g11d3c2evnjhaawa1 foreign key (route_id) references routes (id);
alter table schedule
    add constraint FKepg502x8rrnmqlwcx3u4632wl foreign key (station_id) references stations (id);
alter table schedule
    add constraint FK63icoccyunlv3l5rq6lpmulyv foreign key (train_id) references trains (id);
alter table stations_for_trains
    add constraint FKat58ak30ey3md46jf0nvw2b1j foreign key (station_id) references stations (id);
alter table stations_for_trains
    add constraint FKej8rfv2f1s1p7qqjoh213chj5 foreign key (train_id) references trains (id);
alter table tickets
    add constraint FK1ds262xq345nkvs5o9ptnftwr foreign key (passenger_id) references passengers (id);
alter table tickets
    add constraint FKjndmi4mev27gnbw3m61y1hb8q foreign key (route_id) references routes (id);
alter table tickets
    add constraint FKsdjbjy7xrs49p88m7h0co4wiu foreign key (train_id) references trains (id);

insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, '1991-29-10', 'Glue', 'Gun', '123', 'Gluegun');
insert into roles (passenger_id, roles)
values (1, 'ADMIN');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (2, '1991-29-10', 'Glue', 'Gun', '123', 'Gluegun1');
insert into roles (passenger_id, roles)
values (2, 'USER');
