
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

select * from omnicart_product_db.brands;

select * from omnicart_product_db.categories;

select * from omnicart_product_db.category_brands;

select * from omnicart_product_db.products;

select * from omnicart_product_db.product_variants;

select * from omnicart_product_db.inventories inv;


