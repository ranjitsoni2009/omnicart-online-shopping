
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

select * from brands;

select * from categories;

select * from category_brands;

select * from products;

select * from product_variants;

select * from inventories;


