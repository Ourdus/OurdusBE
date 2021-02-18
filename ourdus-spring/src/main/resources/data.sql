INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test0@gmail.com', '0000', 'test0', '01011112222');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test1@gmail.com', '1111', 'test1', '01022223333');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test2@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test3@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test4@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test5@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test6@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test7@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test8@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test9@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test10@gmail.com', '2222', 'test2', '01033333444');
INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, 'test11@gmail.com', '2222', 'test2', '01033333444');

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



INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 3, 1, 1, '옵션1과옵션2를선택', 2, 400);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 2, 2, 2, '옵션1과옵션2를선택', 2, 200);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 3, 3, 3, '옵션1과옵션2를선택', 1, 400);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 4, 3, 3, '옵션1과옵션2를선택', 5, 500);

INSERT INTO orders(order_id, user_id,  order_price, order_account) VALUES (null, 1, 5000, '무통장입금');
INSERT INTO orders(order_id, user_id,  order_price, order_account) VALUES (null, 2, 1000, '무통장입금');
INSERT INTO orders(order_id, user_id, order_price, order_account) VALUES (null, 3, 4000, '신한카드');
INSERT INTO orders(order_id, user_id, order_price, order_account) VALUES (null, 4, 5000, '계좌이체');

INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 1, 1, '옵션1과옵션2를선택', 2, 400);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 2, 2, '옵션1과옵션2를선택', 2, 200);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 3, 3, '옵션1과옵션2를선택', 1, 400);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 2, 3, 3, '옵션1과옵션2를선택', 5, 500);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 2, 1, 1, '옵션1과옵션2를선택', 5, 500);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 3, 3, 3, '옵션1과옵션2를선택', 5, 500);

INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 1);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소2', '01023122121', '22', '주소22', '세부주소221', 1);

INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 2);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 3);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 4);

INSERT INTO promotion (promotion_name,promotion_description) values ('first','discount');
INSERT INTO promotion (promotion_name,promotion_description) values ('second','discount');
INSERT INTO promotion (promotion_name,promotion_description) values ('third','discount');

INSERT INTO promotion_product(promotion_id,product_id) values (1,1);
INSERT INTO promotion_product(promotion_id,product_id) values (1,2);
INSERT INTO promotion_product(promotion_id,product_id) values (2,1);
INSERT INTO promotion_product(promotion_id,product_id) values (2,2);
INSERT INTO promotion_product(promotion_id,product_id) values (2,3);

INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소3', '010231221121', '123532', '주소1121', '세부주소111', 2);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소4', '010231221121', '123532', '주소1121', '세부주소111', 3);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소5', '010231221121', '123532', '주소1121', '세부주소111', 4);

INSERT INTO review(user_id, order_detail_id, product_id, review_content, review_rate) VALUES(1, 2, 2, '쓸만해요', 50);
INSERT INTO review(user_id, order_detail_id, product_id, review_content, review_rate) VALUES(2, 4, 3, '좋아용', 40);

INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'첫번째 작품 댓글1',1,1);
INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'첫번째 작품 댓글2',1,2);
INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'첫번째 작품 댓글3',1,3);
INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'두번째 작품 댓글1',2,1);
INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'두번째 작품 댓글2',2,2);
INSERT INTO comment(comment_id, comment_content, product_id,user_id) VALUES(null,'세번째 작품 댓글1',3,4);


INSERT INTO c_big_category(big_category_id,big_category_name) VALUES(null,'art');
INSERT INTO c_big_category(big_category_id,big_category_name) VALUES(null,'drink');
INSERT INTO c_big_category(big_category_id,big_category_name) VALUES(null,'dessert');

INSERT INTO c_small_category(small_category_id,big_category_id,small_category_name) VALUES(null,1,'necklace');
INSERT INTO c_small_category(small_category_id,big_category_id,small_category_name) VALUES(null,1,'ring');
INSERT INTO c_small_category(small_category_id,big_category_id,small_category_name) VALUES(null,1,'earring');
INSERT INTO c_small_category(small_category_id,big_category_id,small_category_name) VALUES(null,2,'orange_juice');


INSERT INTO offline_class(class_id,small_category_id,author_id) VALUES(null,3,2);
INSERT INTO offline_class(class_id,small_category_id,author_id) VALUES(null,3,4);
INSERT INTO offline_class(class_id,small_category_id,author_id) VALUES(null,1,1);