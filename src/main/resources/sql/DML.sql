-- Table: reactive.appl_user
insert into reactive.appl_user (id, country, age, name, password, enabled) values (1, 'UK', 30, 'Akshay', 'jk+bb+gdGuu50Nn7hYGE+kGaPintdUxT/GVzT2Qo/K0=', true);
insert into reactive.appl_user (id, country, age, name, password, enabled) values(2, 'UK', 33, 'Ashish', 'dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=', true);
insert into reactive.appl_user (id, country, age, name, password, enabled) values(3, 'UK', 34, 'Praveen', 'cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=', true);
insert into reactive.appl_user (id, country, age, name, password, enabled) values(4, 'UK', 34, 'Parshuram', 'cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=', true);


-- Table: reactive.appl_role
insert into reactive.appl_role (role_id, role_name) values (1, 'ROLE_USER');
insert into reactive.appl_role (role_id, role_name) values (2, 'ROLE_ADMIN');
insert into reactive.appl_role (role_id, role_name) values (3, 'ROLE_SUPERADMIN');

-- Table: reactive.book
insert into reactive.book (id, title, author) values (1, 'Java', 'Paul');
insert into reactive.book (id, title, author) values (2, 'C++', 'Will');
insert into reactive.book (id, title, author) values (3, 'Python', 'John');
insert into reactive.book (id, title, author) values (4, 'Nigel', 'Archie');

-- Table: reactive.user_role
insert into reactive.user_role (user_id, role_id) values(1,3);
insert into reactive.user_role (user_id, role_id) values(2,2);
insert into reactive.user_role (user_id, role_id) values(3,1);
insert into reactive.user_role (user_id, role_id) values(4,1);