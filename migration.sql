insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), 'Администратор', 'ADMIN');
insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), 'Пользователь', 'USER');

insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для аутентификации', 'POST', true, true, '/api/v1/auth/login');

insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для получения ролей', 'GET', true, false, '/api/v1/roles');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для сохранения роля', 'POST', true, false, '/api/v1/roles');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для обновления роля', 'PUT', true, false, '/api/v1/roles');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для удаления роля', 'DELETE', true, false, '/api/v1/roles/*');

insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для регистрации пользователя', 'POST', true, true, '/api/v1/users');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для получения пользователей', 'POST', true, false, '/api/v1/users/filter');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для обновления пользователя', 'PUT', true, false, '/api/v1/users');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
values (nextval('auth_routes_seq'), 'Роут для удаления пользователя', 'DELETE', true, false, '/api/v1/users/*');

insert into auth_permissions(id, description, is_active, name)
values (nextval('auth_permissions_seq'), 'Чтение данных', true, 'READ');
insert into auth_permissions(id, description, is_active, name)
values (nextval('auth_permissions_seq'), 'Удаление данных', true, 'DELETE');
insert into auth_permissions(id, description, is_active, name)
values (nextval('auth_permissions_seq'), 'Запись данных', true, 'WRITE');



