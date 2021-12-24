alter table projects
add cost decimal;

update projects
set cost = 160000
where id = 3;

update projects
set cost = 155000
where id = 1;

update projects
set cost = 115000
where id = 2;

update projects
set cost = 123000
where id = 4;

update projects
set cost = 180000
where id = 5;