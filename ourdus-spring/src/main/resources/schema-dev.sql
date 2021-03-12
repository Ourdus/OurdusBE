CREATE TABLE user
(
    user_id     bigint      NOT NULL AUTO_INCREMENT,
    user_email  varchar(20) NOT NULL,
    user_pw     varchar(20) NOT NULL,
    user_name   varchar(20) NOT NULL,
    user_tel    varchar(20) NOT NULL,
    reg_date    datetime    NOT NULL DEFAULT NOW(),
    user_point  int         NOT NULL DEFAULT 0,
    writer_flag TINYINT(1) NOT NULL DEFAULT false,
    PRIMARY KEY (user_id),
    UNIQUE (user_email)
);

CREATE TABLE address
(
    address_id      bigint      NOT NULL AUTO_INCREMENT,
    address_name    varchar(20) NOT NULL,
    address_phone   varchar(30) NOT NULL,
    address_zipcode varchar(20) NOT NULL,
    address_main    varchar(50) NOT NULL,
    address_sub     varchar(50) NOT NULL,
    user_id         bigint      NOT NULL,
    PRIMARY KEY (address_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE product_category
(
    category_id   bigint      NOT NULL AUTO_INCREMENT,
    category_name varchar(20) NOT NULL,
    category_image varchar(100) ,
    PRIMARY KEY (category_id)
);

CREATE TABLE product
(
    product_id         bigint      NOT NULL AUTO_INCREMENT,
    author_id          bigint      NOT NULL,
    category_id        bigint      NOT NULL,
    product_name       varchar(20) NOT NULL,
    product_info       MEDIUMTEXT NULL,
    product_price      int         NOT NULL DEFAULT 0,
    product_rate       int         NOT NULL DEFAULT 0,
    product_review_num int         NOT NULL DEFAULT 0,
    product_hit        int         NOT NULL DEFAULT 0,
    product_purchase   int         NOT NULL DEFAULT 0,
    product_option_num int         NOT NULL DEFAULT 0,
    PRIMARY KEY (product_id),
    FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES product_category (category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE product_parent_option
(
    product_parent_option_id bigint      NOT NULL AUTO_INCREMENT,
    product_id               bigint      NOT NULL,
    option_name              varchar(50) NOT NULL,
    PRIMARY KEY (product_parent_option_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);


CREATE TABLE product_child_option
(
    product_child_option_id  bigint      NOT NULL AUTO_INCREMENT,
    product_parent_option_id bigint      NOT NULL,
    product_id               bigint      NOT NULL,
    option_name              varchar(50) NOT NULL,
    option_price             int         NOT NULL DEFAULT 0,
    PRIMARY KEY (product_child_option_id),
    FOREIGN KEY (product_parent_option_id) REFERENCES product_parent_option (product_parent_option_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE product_image
(
    image_id   bigint       NOT NULL AUTO_INCREMENT,
    product_id bigint       NOT NULL,
    image      varchar(100) NOT NULL,
    PRIMARY KEY (image_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE cart_detail
(
    cart_id              bigint   NOT NULL AUTO_INCREMENT,
    user_id              bigint   NOT NULL,
    author_id            bigint   NOT NULL,
    product_id           bigint   NOT NULL,
    option_info          varchar(100) NULL,
    product_num          int      NOT NULL DEFAULT 1,
    cart_in_date         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    product_detail_price int      NOT NULL DEFAULT 0,
    PRIMARY KEY (cart_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE orders
(
    order_id      bigint      NOT NULL AUTO_INCREMENT,
    user_id       bigint      NOT NULL,
    address_id    bigint      NOT NULL,
    order_date    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    order_price   int         NOT NULL DEFAULT 0,
    order_account varchar(50) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE RESTRICT ON UPDATE RESTRICT

);

CREATE TABLE order_detail
(
    order_detail_id      bigint NOT NULL AUTO_INCREMENT,
    order_id             bigint NOT NULL,
    author_id            bigint NOT NULL,
    product_id           bigint NOT NULL,
    option_info          varchar(100) NULL,
    product_num          int    NOT NULL DEFAULT 1,
    product_detail_price int    NOT NULL DEFAULT 0,
    PRIMARY KEY (order_detail_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);



create table promotion
(
    promotion_id          bigint       NOT NULL AUTO_INCREMENT,
    promotion_name        varchar(100) NOT NULL,
    promotion_description MEDIUMTEXT,
    promotion_start_date  datetime DEFAULT CURRENT_TIMESTAMP(),
    promotion_end_date    datetime DEFAULT CURRENT_TIMESTAMP(),
    promotion_img         varchar(50),
    PRIMARY KEY (promotion_id)
);
create table promotion_product
(
    promotion_product_id bigint NOT NULL AUTO_INCREMENT,
    promotion_id         bigint NOT NULL,
    product_id           bigint NOT NULL,
    PRIMARY KEY (promotion_product_id),
    FOREIGN KEY (promotion_id) REFERENCES promotion (promotion_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE comment
(
    comment_id      bigint      NOT NULL AUTO_INCREMENT,
    comment_content MEDIUMTEXT  NOT NULL,
    product_id      bigint      NOT NULL,
    user_id         bigint      NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE review
(
    review_id       bigint       NOT NULL AUTO_INCREMENT,
    user_id         bigint       NOT NULL,
    order_detail_id bigint       NOT NULL,
    product_id      bigint       NOT NULL,
    review_content  varchar(500) NOT NULL,
    review_date     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    review_rate     int          NOT NULL DEFAULT 50,
    PRIMARY KEY (review_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (order_detail_id) REFERENCES order_detail (order_detail_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE c_big_category
(
    big_category_id   bigint      NOT NULL AUTO_INCREMENT,
    big_category_name varchar(20) NOT NULL,
    PRIMARY KEY (big_category_id)
);

CREATE TABLE c_small_category
(
    small_category_id   bigint      NOT NULL AUTO_INCREMENT,
    big_category_id     bigint      NOT NULL,
    small_category_name varchar(20) NOT NULL,
    PRIMARY KEY (small_category_id),
    FOREIGN KEY (big_category_id) REFERENCES c_big_category (big_category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE TABLE offline_class
(
    class_id          bigint NOT NULL auto_increment,
    small_category_id bigint NOT NULL,
    author_id         bigint NOT NULL,
    class_price       int DEFAULT 0,
    class_description MEDIUMTEXT,
    class_hit         int DEFAULT 0,
    class_purchase    int DEFAULT 0,
    class_duration    int DEFAULT 0,
    class_level       varchar(20),
    class_place       varchar(100),
    user_max          int DEFAULT 0,
    class_like        int DEFAULT 0,
    class_rate        int DEFAULT 0,
    class_name        varchar(100),
    PRIMARY KEY (class_id),
    FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (small_category_id) REFERENCES c_small_category (small_category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE TABLE online_class_category
(
    online_category_id   bigint      NOT NULL AUTO_INCREMENT,
    online_category_name varchar(30) NOT NULL,
    PRIMARY KEY (online_category_id)
);

CREATE TABLE online_class
(
    online_class_id          bigint       NOT NULL auto_increment,
    online_category_id       bigint       NOT NULL,
    author_id                bigint       NOT NULL,
    online_class_name        varchar(100) NOT NULL,
    online_class_price       int          NOT NULL DEFAULT 0 NOT NULL,
    online_class_description MEDIUMTEXT   NOT NULL,
    online_class_duration    int          NOT NULL DEFAULT 0 NOT NULL,
    online_class_level       varchar(20)  NOT NULL,
    online_class_start_date  datetime     NOT NULL default current_timestamp(),
    preparation_flag         boolean      NOT NULL DEFAULT false,
    online_class_hit         int          NOT NULL DEFAULT 0,
    online_class_purchase    int          NOT NULL DEFAULT 0,
    online_class_like        int          NOT NULL DEFAULT 0,
    online_class_rate        int          NOT NULL DEFAULT 0,
    online_class_image       varchar(100) NULL,
    online_class_video       varchar(100) NULL,
    PRIMARY KEY (online_class_id),
    FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (online_category_id) REFERENCES online_class_category (online_category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);


CREATE TABLE c_reservation
(
    booking_id  bigint NOT NULL auto_increment,
    class_id bigint NOT NULL,
    dates datetime NULL default current_timestamp(),
    times datetime NULL default current_timestamp(),
    user_no int NULL,
    reservation_flag boolean NULL,
    PRIMARY KEY(booking_id),
    FOREIGN KEY (class_id) REFERENCES offline_class (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE c_order
(
    order_id  bigint NOT NULL auto_increment,
    user_id  bigint  NOT NULL,
    booking_id bigint NOT NULL,
    class_id bigint NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY (booking_id) REFERENCES c_reservation (booking_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (class_id) REFERENCES c_reservation (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY(user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE offline_class_image
(
    image_id   bigint       NOT NULL AUTO_INCREMENT,
    class_id bigint       NOT NULL,
    image      varchar(100) NOT NULL,
    PRIMARY KEY (image_id),
    FOREIGN KEY (class_id) REFERENCES offline_class (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);



CREATE TABLE preparation
(
    preparation_id    bigint NOT NULL AUTO_INCREMENT,
    online_class_id   bigint NOT NULL,
    preparation_price int    NOT NULL,
    preparation_stock int    NOT NULL,
    PRIMARY KEY (preparation_id),
    FOREIGN KEY (online_class_id) REFERENCES online_class (online_class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- CREATE TABLE class_order
-- (
--
-- );

CREATE TABLE online_class_comment
(
    online_comment_id      bigint      NOT NULL AUTO_INCREMENT,
    online_comment_content MEDIUMTEXT NOT NULL,
    online_class_id      bigint      NOT NULL,
    user_id         bigint      NOT NULL,
    PRIMARY KEY (online_comment_id ),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (online_class_id) REFERENCES online_class (online_class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE offline_class_comment
(
    offline_comment_id      bigint      NOT NULL AUTO_INCREMENT,
    offline_comment_content MEDIUMTEXT NOT NULL,
    class_id      bigint      NOT NULL,
    user_id         bigint      NOT NULL,
    PRIMARY KEY (offline_comment_id ),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (class_id) REFERENCES offline_class (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE online_class_review
(
    online_review_id       bigint       NOT NULL AUTO_INCREMENT,
    user_id         bigint       NOT NULL,
--     online_order_detail_id bigint       NOT NULL,
    online_class_id      bigint       NOT NULL,
    online_review_content  mediumtext NOT NULL,
    online_review_date     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    online_review_rate     int
              NOT NULL DEFAULT 50,
    PRIMARY KEY (online_review_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
--     FOREIGN KEY (online_order_detail_id) REFERENCES order_detail (order_detail_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (online_class_id) REFERENCES online_class (online_class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE offline_class_review
(
    offline_review_id       bigint       NOT NULL AUTO_INCREMENT,
    user_id         bigint       NOT NULL,
--     online_order_detail_id bigint       NOT NULL,
    class_id      bigint       NOT NULL,
    offline_review_content  varchar(500) NOT NULL,
    offline_review_date     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    offline_review_rate     int          NOT NULL DEFAULT 50,
    PRIMARY KEY (offline_review_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
--     FOREIGN KEY (offline_order_detail_id) REFERENCES offline_order_detail (offline_order_detail_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (class_id) REFERENCES offline_class (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);