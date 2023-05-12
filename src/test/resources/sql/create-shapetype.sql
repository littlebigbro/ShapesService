-- delete previously added shapetypes --
delete from shapetype;
-- add shapetypes --
insert into shapetype (shapetype_id, created, name, points_count, systemname, updated)
    values (1, now(), 'круг', 1, 'circle', now());

insert into shapetype (shapetype_id, created, name, points_count, systemname, updated)
    values (2, now(), 'треугольник', 3, 'triangle', now());

insert into shapetype (shapetype_id, created, name, points_count, systemname, updated)
    values (3, now(), 'квадрат', 4, 'rectangle', now());

alter sequence shapetype_id_seq restart with 4;