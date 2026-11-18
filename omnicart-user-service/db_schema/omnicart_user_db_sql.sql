select * from omnicart_user_db.users;

select * from omnicart_user_db.roles;
/*
insert into roles(role, created_at, updated_at) values('ADMIN', current_timestamp, current_timestamp);
insert into roles(role, created_at, updated_at) values('CLIENT', current_timestamp, current_timestamp);
insert into roles(role, created_at, updated_at) values('VENDOR', current_timestamp, current_timestamp);
*/

select * from omnicart_user_db.permissions;

/*
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATED_CATEGORY', 'Create Category Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('UPDATE_CATEGORY', 'Get Category Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_CATEGORY', 'Update Category Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('DELETE_CATEGORY', 'Delete Category Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATE_PRODUCT', 'Create Product Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_PRODUCT', 'Get Product Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('UPDATE_PRODUCT', 'Update Product Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('DELETE_PRODUCT', 'Delete Product Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATE_BRAND', 'Create Brand Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_BRAND', 'Get Brand Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('UPDATE_BRAND', 'Update Brand Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('DELETE_BRAND', 'Delete Brand Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATE_INVENTORY', 'Create Inventory Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_INVENTORY', 'Get Inventory Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('UPDATE_INVENTORY', 'Update Inventory Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('DELETE_INVENTORY', 'Delete Inventory Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('RESERVE_INVENTORY', 'Delete Inventory Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATE_VENDOR', 'Create Vendor Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_VENDOR', 'Get Vendor Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('UPDATE_VENDOR', 'Update Vendor Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('DELETE_VENDOR', 'Delete Vendor Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('ADD_ITEM_IN_CART', 'Add Item in Cart Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('READ_OWN_CART', 'Read Cart Items Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CLEAR_OWN_CART', 'Delete Items from Cart Permission', current_timestamp(), current_timestamp());

insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CREATE_ORDER', 'Create order Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('CANCEL_ORDER', 'Cancel order Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('RETURN_ORDER', 'Return order Permission', current_timestamp(), current_timestamp());
insert into omnicart_user_db.permissions(permission_code, description, created_at, updated_at) values('REPLACE_ORDER', 'Replace order Permission', current_timestamp(), current_timestamp());
*/



select * from user_roles;

/*
insert into user_roles(user_id, role_id, created_at, updated_at) values(1, 1, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(2, 2, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(3, 3, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(1, 2, current_timestamp(), current_time());
insert into user_roles(user_id, role_id, created_at, updated_at) values(3, 2, current_timestamp(), current_time());
*/

select * from omnicart_user_db.role_permissions;
/*
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 1, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 2, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 3, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 4, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 2, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 5, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 6, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 7, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 8, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 9, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 9, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 10, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 11, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 12, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 32, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 32, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 32, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 13, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 15, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 21, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 22, current_timestamp(), current_time());
insert into role_permissions(role_id, permission_id, created_at, updated_at) values(1, 23, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 21, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 22, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 23, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 21, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 22, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(3, 23, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 24, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 25, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 26, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(2, 27, current_timestamp(), current_time());

insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 28, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 29, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 30, current_timestamp(), current_time());
insert into omnicart_user_db.role_permissions(role_id, permission_id, created_at, updated_at) values(1, 31, current_timestamp(), current_time());


*/

select * from api_permissions;



#############################General Queries#################################
Select * from omnicart_user_db.users;
select * from omnicart_user_db.user_roles ur where ur.user_id=1;
select * from omnicart_user_db.roles;
select * from omnicart_user_db.permissions;
select * from omnicart_user_db.role_permissions rp where rp.permission_id=9;
select * from omnicart_user_db.permissions p inner join omnicart_user_db.role_permissions rp on p.id = rp.permission_id where rp.role_id in (1,2);

select * from omnicart_user_db.vendor_details;



