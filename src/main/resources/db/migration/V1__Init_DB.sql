create sequence point_id_seq start 1 increment 1;

create sequence radius_id_seq start 1 increment 1;

create sequence shape_id_seq start 1 increment 1;

create sequence shapetype_id_seq start 1 increment 1;

create table point (
    point_id int8 not null,
    x float8 not null,
    y float8 not null,
    shape_id int8,
    primary key (point_id)
);

create table radius_info (
    radius_id int8 not null,
    radius float8 not null,
    shape_id int8,
    primary key (radius_id)
);

create table shape (
    shape_id int8 not null,
    shapetype_id int8,
    created timestamp,
    updated timestamp,
    primary key (shape_id)
);

create table shapetype (
    shapetype_id int8 not null,
    systemname varchar(255) not null,
    name varchar(255) not null,
    points_count int4 not null check (points_count>=1),
    created timestamp,
    updated timestamp,
    primary key (shapetype_id)
);

alter table shapetype
    add constraint shapetype_systemname_uk
    unique (systemname);

alter table shapetype
    add constraint shapetype_name_uk
    unique (name);

alter table shapetype
    add constraint shapetype_points_count_uk
    unique (points_count);

alter table point
    add constraint point_shape_fk
    foreign key (shape_id) references shape;

alter table radius_info
    add constraint radius_info_shape_fk
    foreign key (shape_id) references shape;

alter table shape
    add constraint shape_shapetype_fk
    foreign key (shapetype_id) references shapetype;