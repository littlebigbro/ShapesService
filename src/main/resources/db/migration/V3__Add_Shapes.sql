-- add shapes --
insert into shape (shape_id, shapetype_id, created, updated)
    values (1, 1, now(), now());

insert into shape (shape_id, shapetype_id, created, updated)
    values (2, 2, now(), now());

insert into shape (shape_id, shapetype_id, created, updated)
    values (3, 3, now(), now());

-- add radius info--
insert into radius_info (radius_id, radius, shape_id)
    values (1, 1, 1);

-- points --
insert into point (point_id, x, y, shape_id)
    values (1, 0, 0, 1);

insert into point (point_id, x, y, shape_id)
    values (2, 0, 0, 2), (3, 0, 3, 2), (4, 3, 0, 2);

insert into point (point_id, x, y, shape_id)
    values (5, 0, 0, 3), (6, 0, 2, 3), (7, 2, 2, 3), (8, 2, 0, 3);