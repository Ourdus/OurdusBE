INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test0@gmail.com', '0000', 'test0', '01011112222');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test1@gmail.com', '1111', 'test1', '01022223333');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test2@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test3@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test4@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test5@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test6@gmail.com', '2222', 'test2', '01033333444');


INSERT INTO product_category(category_id, category_name) VALUES (null, '음료');
INSERT INTO product_category(category_id, category_name) VALUES (null, '디저트');


INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 1, 1, 'product1_by_test0');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 2, 2, 'product2_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 3, 1, 'product3_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 4, 1, 'product4_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 5, 1, 'product5_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 6, 1, 'product6_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 7, 1, 'product7_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 8, 1, 'product8_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 9, 1, 'product9_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 10, 1, 'product10_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 11, 1, 'product11_by_test1');
INSERT INTO product(product_id, author_id, category_id, product_name) VALUES (null, 12, 1, 'product12_by_test1');

INSERT INTO product_option(option_id, product_id, option_name, option_price) VALUES (null, 1, '1옵션1', 0);
INSERT INTO product_option(option_id, product_id, option_name, option_price) VALUES (null, 1, '1옵션2', 100);
INSERT INTO product_option(option_id, product_id, option_name, option_price) VALUES (null, 2, '2옵션1', 400);
INSERT INTO product_option(option_id, product_id, option_name, option_price) VALUES (null, 2, '2옵션2', 1000);


