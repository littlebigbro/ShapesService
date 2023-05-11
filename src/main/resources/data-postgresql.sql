-- add shapetypes --
insert into shapetype (shapetype_id, created, name, points_count, systemname, updated) values (1, now(), 'круг', 1, 'circle', now());
insert into shapetype (shapetype_id, created, name, points_count, systemname, updated) values (2, now(), 'треугольник', 3, 'triangle', now());
insert into shapetype (shapetype_id, created, name, points_count, systemname, updated) values (3, now(), 'квадрат', 4, 'rectangle', now());
-- add shapes --
insert into shape (shape_id, created, updated, shapetype_id) values (1, now(), now(), 1);
insert into shape (shape_id, created, updated, shapetype_id) values (2, now(), now(), 2);
insert into shape (shape_id, created, updated, shapetype_id) values (3, now(), now(), 3);
-- add radius info--
insert into radius_info (radius_id, radius, shape_id) values (1, 1, 1);
-- points --
insert into point (point_id, x, y, shape_id) values (1, 0, 0, 1);

insert into point (point_id, x, y, shape_id) values (2, 0, 0, 2);
insert into point (point_id, x, y, shape_id) values (3, 0, 3, 2);
insert into point (point_id, x, y, shape_id) values (4, 3, 0, 2);

insert into point (point_id, x, y, shape_id) values (5, 0, 0, 3);
insert into point (point_id, x, y, shape_id) values (6, 0, 2, 3);
insert into point (point_id, x, y, shape_id) values (7, 2, 2, 3);
insert into point (point_id, x, y, shape_id) values (8, 2, 0, 3);