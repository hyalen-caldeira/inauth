insert into products (product_id, upc, sku, description, price) values ('1', '1257833283', '9394550220002', 'Diva Jeans', 39.99);
insert into products (product_id, upc, sku, description, price) values ('2', '1358743283', '7394650110003', 'Polo Shirt', 19.99);
insert into products (product_id, upc, sku, description, price) values ('3', '1458843283', '7394750120000', 'Floral Swing Skirt', 69.99);
insert into products (product_id, upc, sku, description, price) values ('4', '1358753283', '7394850130001', 'Denim Short', 29.99);
insert into products (product_id, upc, sku, description, price) values ('5', '1258793283', '7394950140000', 'True Skinny Jeans', 49.99);

insert into orders (order_id, order_number, tax_percent, status) values ('1', 'RTL_1001', 10, 'SHIPPED');
insert into orders (order_id, order_number, discount, tax_percent, status) values ('2', 'RTL_1002', 15.55, 10, 'FULFILLED');
insert into orders (order_id, order_number, discount, tax_percent, status) values ('3', 'RTL_1003', 19.99, 8.5, 'SHIPPED');
insert into orders (order_id, order_number, tax_percent, status) values ('4', 'RTL_1004', 10, 'SHIPPED');
insert into orders (order_id, order_number, tax_percent, status) values ('5', 'RTL_1005', 9.5, 'FULFILLED');

insert into order_product (order_id, product_id) values ('1', '1');
insert into order_product (order_id, product_id) values ('1', '2');
insert into order_product (order_id, product_id) values ('2', '2');
insert into order_product (order_id, product_id) values ('3', '2');
insert into order_product (order_id, product_id) values ('3', '3');
insert into order_product (order_id, product_id) values ('3', '5');
insert into order_product (order_id, product_id) values ('4', '1');
insert into order_product (order_id, product_id) values ('4', '3');
insert into order_product (order_id, product_id) values ('5', '5');
