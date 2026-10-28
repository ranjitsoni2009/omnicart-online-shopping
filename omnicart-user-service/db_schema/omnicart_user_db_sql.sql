select * from users;

select * from roles;
/*
insert into roles(role, created_at, updated_at) values('ADMIN', current_timestamp, current_timestamp);
insert into roles(role, created_at, updated_at) values('CLIENT', current_timestamp, current_timestamp);
insert into roles(role, created_at, updated_at) values('VENDOR', current_timestamp, current_timestamp);
*/

select * from permissions;

/*
insert into permissions(permission_code, description, created_at, updated_at) values('CREATE_PRODUCT', 'Create Product Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('READ_PRODUCT', 'Get Product Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('UPDATE_PRODUCT', 'Update Product Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('DELETE_PRODUCT', 'Delete Product Permission', current_timestamp(), current_timestamp());

insert into permissions(permission_code, description, created_at, updated_at) values('CREATE_BRAND', 'Create Brand Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('READ_BRAND', 'Get Brand Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('UPDATE_BRAND', 'Update Brand Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('DELETE_BRAND', 'Delete Brand Permission', current_timestamp(), current_timestamp());

insert into permissions(permission_code, description, created_at, updated_at) values('CREATE_INVENTORY', 'Create Inventory Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('READ_INVENTORY', 'Get Inventory Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('UPDATE_INVENTORY', 'Update Inventory Permission', current_timestamp(), current_timestamp());
insert into permissions(permission_code, description, created_at, updated_at) values('DELETE_INVENTORY', 'Delete Inventory Permission', current_timestamp(), current_timestamp());

*/



select * from user_roles;

/*
insert into user_roles(user_id, role_id, created_at, updated_at) values(1, 1, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(2, 2, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(3, 3, current_timestamp(), current_time());
*/

select * from role_permissions;
/*
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 1, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 2, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 3, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 4, current_timestamp(), current_time());

insert into role_permissions(role_id, permission_id, created_at, updated_at) values(2, 2, current_timestamp(), current_time());

insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 5, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 6, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 7, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 8, current_timestamp(), current_time());

insert into role_permissions(role_id, permission_id, created_at, updated_at) values(3, 9, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(3, 10, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(3, 11, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(3, 12, current_timestamp(), current_time());
*/

select * from api_permissions;

/*
INSERT INTO api_permissions
    (http_method, url_pattern, permission_id, is_active, created_at, updated_at)
VALUES
    ('POST',   '/api/v1/products',     1, TRUE, current_timestamp(), current_timestamp()),
    ('PUT',    '/api/v1/products/{id}', 3, TRUE, current_timestamp(), current_timestamp()),
    ('PATCH',  '/api/v1/products/{id}', 3, TRUE, current_timestamp(), current_timestamp()),
    ('DELETE', '/api/v1/products/{id}', 4, TRUE, current_timestamp(), current_timestamp()),
    ('GET',    '/api/v1/products',     2,    TRUE, current_timestamp(), current_timestamp()),
    ('GET',    '/api/v1/products/{id}', 2,    TRUE, current_timestamp(), current_timestamp());
*/

select * from permissions p inner join role_permissions rp on p.id = rp.permission_id where rp.role_id=3;

select * from vendor_details;

