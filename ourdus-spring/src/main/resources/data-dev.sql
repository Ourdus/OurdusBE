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

INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 1);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소2', '01023122121', '22', '주소22', '세부주소221', 1);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 2);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 3);
INSERT INTO address(address_id, address_name, address_phone, address_zipcode,address_main,address_sub, user_id) VALUES(null, '주소1', '010231221121', '123532', '주소1121', '세부주소111', 4);

INSERT INTO product_category(category_id, category_name,category_image) VALUES (null, '음료','image');
INSERT INTO product_category(category_id, category_name,category_image) VALUES (null, '디저트','image');


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
INSERT INTO product(product_id, author_id, category_id, product_name, product_info) VALUES (null, 12, 1, 'product12_by_test1', '작품내용입니다.');

INSERT INTO product_image(image_id, product_id, image) VALUES (null, 1, 'https://image.idus.com/image/files/35a177d4f4064b7f96e89f55ac495d33_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 1, 'https://image.idus.com/image/files/71759e1b9f8f4c469a0e0efef403c892_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 1, 'https://image.idus.com/image/files/bebec784896d48dbb6ca0a417a5d679b_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 2, 'https://image.idus.com/image/files/910a0bad1c264cea969409446c522b5b_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 2, 'https://image.idus.com/image/files/0ad82b217bfb48279b13695ab767b576_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 2, 'https://image.idus.com/image/files/e6b10690690f42ec9b8d37889803c1f7_720.jpg');
INSERT INTO product_image(image_id, product_id, image) VALUES (null, 3, 'https://image.idus.com/image/files/17e8ad331baf4e3193f082ad51de68d6_720.png');


INSERT INTO product_parent_option(product_parent_option_id, product_id, option_name) VALUES (null, 1, '1큰옵션1');
INSERT INTO product_parent_option(product_parent_option_id, product_id, option_name) VALUES (null, 1, '1큰옵션2');
INSERT INTO product_parent_option(product_parent_option_id, product_id, option_name) VALUES (null, 2, '2큰옵션1');
INSERT INTO product_parent_option(product_parent_option_id, product_id, option_name) VALUES (null, 2, '2큰옵션2');

INSERT INTO product_child_option(product_child_option_id, product_parent_option_id, product_id, option_name, option_price) VALUES (null,1, 1, '1큰옵션의작은옵션1', 500);
INSERT INTO product_child_option(product_child_option_id, product_parent_option_id, product_id, option_name, option_price) VALUES (null,1, 1, '1큰옵션의작은옵션2', 1000);
INSERT INTO product_child_option(product_child_option_id, product_parent_option_id, product_id, option_name, option_price) VALUES (null,2, 2, '2큰옵션의작은옵션1', 0);
INSERT INTO product_child_option(product_child_option_id, product_parent_option_id, product_id, option_name, option_price) VALUES (null,2, 2, '2큰옵션의작은옵션2', 300);
INSERT INTO product_child_option(product_child_option_id, product_parent_option_id, product_id, option_name, option_price) VALUES (null,2, 2, '2큰옵션의작은옵션3', 800);


INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 3, 1, 1, '옵션1과옵션2를선택', 2, 400);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 2, 2, 2, '옵션1과옵션2를선택', 2, 200);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 3, 3, 3, '옵션1과옵션2를선택', 1, 400);
INSERT INTO cart_detail(cart_id, user_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES (null, 4, 3, 3, '옵션1과옵션2를선택', 5, 500);

INSERT INTO orders(order_id, user_id, address_id, order_price, order_account) VALUES (null, 1, 1, 5000, '무통장입금');
INSERT INTO orders(order_id, user_id, address_id, order_price, order_account) VALUES (null, 2, 3, 1000, '무통장입금');
INSERT INTO orders(order_id, user_id, address_id, order_price, order_account) VALUES (null, 3, 4, 4000, '신한카드');
INSERT INTO orders(order_id, user_id, address_id, order_price, order_account) VALUES (null, 4, 5, 5000, '계좌이체');

INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 1, 1, '옵션1과옵션2를선택', 2, 400);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 2, 2, '옵션1과옵션2를선택', 2, 200);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 1, 3, 3, '옵션1과옵션2를선택', 1, 400);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 2, 3, 3, '옵션1과옵션2를선택', 5, 500);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 2, 1, 1, '옵션1과옵션2를선택', 5, 500);
INSERT INTO order_detail(order_detail_id, order_id, author_id, product_id, option_info, product_num, product_detail_price) VALUES(null, 3, 3, 3, '옵션1과옵션2를선택', 5, 500);

INSERT INTO promotion (promotion_name,promotion_description) values ('first','discount');
INSERT INTO promotion (promotion_name,promotion_description) values ('second','discount');
INSERT INTO promotion (promotion_name,promotion_description) values ('third','discount');

INSERT INTO promotion_product(promotion_id,product_id) values (1,1);
INSERT INTO promotion_product(promotion_id,product_id) values (1,2);
INSERT INTO promotion_product(promotion_id,product_id) values (2,1);
INSERT INTO promotion_product(promotion_id,product_id) values (2,2);
INSERT INTO promotion_product(promotion_id,product_id) values (2,3);

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


INSERT INTO offline_class(class_id,small_category_id,author_id,class_price,class_description,class_purchase,class_duration,class_level,class_place,user_max,class_like,class_name) VALUES(null,1,2,200000, '클래스 설명 1',55,3,'쉬움','seoul gangnam',30,15,'물 안줘도 되는! 스투키 캔들 만들기1');
INSERT INTO offline_class(class_id,small_category_id,author_id,class_price,class_description,class_purchase,class_duration,class_level,class_place,user_max,class_like,class_name) VALUES(null,3,3,200000, '클래스 설명 2',55,3,'어려움','seoul gangnams',30,15,'케익만들기');
INSERT INTO offline_class(class_id,small_category_id,author_id,class_price,class_description,class_purchase,class_duration,class_level,class_place,user_max,class_like,class_name) VALUES(null,1,1,200000, '클래스 설명 3',55,3,'쉬움','busan hae',30,15,'빵만들기');

INSERT INTO online_class_category(online_category_id, online_category_name) VALUES(null,'유화/아크릴화');
INSERT INTO online_class_category(online_category_id, online_category_name) VALUES(null,'수채화');


INSERT INTO online_class(online_class_id, online_category_id, author_id, online_class_name, online_class_price, online_class_description, online_class_duration, online_class_level) VALUES(null,1,1, '온라인작품1', 1000, '1온라인작품설명', 100, '쉬움');
INSERT INTO online_class(online_class_id, online_category_id, author_id, online_class_name, online_class_price, online_class_description, online_class_duration, online_class_level) VALUES(null,1,2, '온라인작품2', 2000, '2온라인작품설명', 200, '어려움');
INSERT INTO online_class(online_class_id, online_category_id, author_id, online_class_name, online_class_price, online_class_description, online_class_duration, online_class_level) VALUES(null,2,2, '온라인작품3', 3000, '3온라인작품설명', 300, '어려울걸');
INSERT INTO online_class(online_class_id, online_category_id, author_id, online_class_name, online_class_price, online_class_description, online_class_duration, online_class_level) VALUES(null,2,3, '온라인작품4', 4000, '4온라인작품설명', 400, '보통');

INSERT INTO c_reservation(booking_id,class_id,class_user_no) values (null,3,5);
INSERT INTO c_reservation(booking_id,class_id,class_user_no) values (null,2,5);
INSERT INTO c_reservation(booking_id,class_id,class_user_no) values (null,3,5);

INSERT INTO c_order(order_id,user_id,booking_id,class_id) values (null,2,1,3);
INSERT INTO c_order(order_id,user_id,booking_id,class_id) values (null,1,1,3);
INSERT INTO c_order(order_id,user_id,booking_id,class_id) values (null,3,1,3);
INSERT INTO c_order(order_id,user_id,booking_id,class_id) values (null,5,1,2);
INSERT INTO c_order(order_id,user_id,booking_id,class_id) values (null,7,1,2);

INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 1, 1);
INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 2, 1);
INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 3, 1);
INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 1, 2);
INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 4, 2);
INSERT INTO online_class_order(online_order_id, online_class_id, user_id) VALUES(null, 1, 3);


INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'첫번째 온라인클래스 댓글1',1,1);
INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'첫번째 온라인클래스 댓글2',1,2);
INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'두번째 온라인클래스 댓글1',2,3);
INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'세번째 온라인클래스 댓글1',3,1);
INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'두번째 온라인클래스 댓글2',2,2);
INSERT INTO online_class_comment(online_comment_id, online_comment_content, online_class_id,user_id) VALUES(null,'첫번째 온라인클래스 댓글3',1,4);

INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content, class_id,user_id) VALUES(null,'첫번째 오프라인클래스 댓글1',1,1);
INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content, class_id,user_id) VALUES(null,'첫번째 오프라인클래스 댓글2',1,2);
INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content,class_id,user_id) VALUES(null,'두번째 오프라인클래스 댓글1',2,3);
INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content, class_id,user_id) VALUES(null,'세번째 오프라인클래스 댓글1',3,1);
INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content, class_id,user_id) VALUES(null,'두번째 오프라인클래스 댓글2',2,2);
INSERT INTO offline_class_comment(offline_comment_id, offline_comment_content, class_id,user_id) VALUES(null,'첫번째 오프라인클래스 댓글3',1,4);

INSERT INTO online_class_review(user_id, online_class_id, online_review_content, online_review_rate) VALUES(1, 1, '쓸만해요', 50);
INSERT INTO online_class_review(user_id, online_class_id, online_review_content, online_review_rate) VALUES(1, 2, '좋아해요', 45);
INSERT INTO online_class_review(user_id, online_class_id, online_review_content, online_review_rate) VALUES(2, 1, '별로야', 44);
INSERT INTO online_class_review(user_id, online_class_id, online_review_content, online_review_rate) VALUES(2, 3, '그냥저냥', 25);

INSERT INTO offline_class_review(user_id, class_id, offline_review_content, offline_review_rate) VALUES(1, 1, '쓸만해요', 50);
INSERT INTO offline_class_review(user_id, class_id, offline_review_content, offline_review_rate) VALUES(1, 2, '좋아해요', 45);
INSERT INTO offline_class_review(user_id, class_id, offline_review_content, offline_review_rate) VALUES(2, 1, '별로야', 44);
INSERT INTO offline_class_review(user_id, class_id, offline_review_content, offline_review_rate) VALUES(2, 3, '그냥저냥', 25);

INSERT INTO offline_class_image(class_id,image) VALUES(1,'https://image.idus.com/image/files/7d992ccb52d142618ca885d0c435b5d2_720.jpg');
INSERT INTO offline_class_image(class_id,image) VALUES(1,'https://image.idus.com/image/files/f89fddb344ea4f518566e50a1ff4b166_720.jpg');
INSERT INTO offline_class_image(class_id,image) VALUES(1,'https://image.idus.com/image/files/b035e738a1b540a0b90f729e481ec075_720.jpg');

