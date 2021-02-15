drop table if exists user;
drop table if exists product_category;
drop table if exists product;
drop table if exists product_option;
drop table if exists comment;

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
    CONSTRAINT unique_user_email UNIQUE (user_email)
);


CREATE TABLE product_category
(
    category_id   bigint      NOT NULL AUTO_INCREMENT,
    category_name varchar(20) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE product
(
    product_id         bigint      NOT NULL AUTO_INCREMENT,
    author_id          bigint      NOT NULL,
    category_id        bigint      NOT NULL,
    product_name       varchar(20) NOT NULL,
    product_price      int         NOT NULL DEFAULT 0,
    product_rate       int         NOT NULL DEFAULT 0,
    product_review_num int         NOT NULL DEFAULT 0,
    product_hit        int         NOT NULL DEFAULT 0,
    product_purchase   int         NOT NULL DEFAULT 0,
    product_option_num int         NOT NULL DEFAULT 0,
    PRIMARY KEY (product_id),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES product_category (category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE product_option
(
    option_id    bigint      NOT NULL AUTO_INCREMENT,
    product_id   bigint      NOT NULL,
    option_name  varchar(20) NOT NULL,
    option_price int         NOT NULL DEFAULT 0,
    PRIMARY KEY (option_id, product_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE product_image
(
    image_id   bigint NOT NULL AUTO_INCREMENT,
    product_id bigint CONSTRAINT fk_product_id NOT NULL,
    image_file blob   NOT NULL,
    PRIMARY KEY (image_id)
);

CREATE TABLE cart_detail
(
    cart_id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint CONSTRAINT fk_user_id NOT NULL,
    author_id bigint CONSTRAINT fk_author_id NOT NULL,
    product_id bigint CONSTRAINT fk_product_id NOT NULL,
    option_info varchar(100) NULL,
    product_num int NOT NULL DEFAULT 1,
    cart_in_date datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    product_detail_price int NOT NULL DEFAULT 0,
    PRIMARY KEY (cart_id)
);

CREATE TABLE orders
(
    order_id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint CONSTRAINT fk_user_id NOT NULL,
    order_date datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    order_price int NOT NULL DEFAULT 0,
    order_account varchar(50) NOT NULL,
    PRIMARY KEY (order_id)
);

CREATE TABLE order_detail
(
    order_detail_id      bigint NOT NULL AUTO_INCREMENT,
    order_id             bigint NOT NULL,
    author_id            bigint
        CONSTRAINT fk_author_id NOT NULL,
    product_id           bigint
        CONSTRAINT fk_product_id NOT NULL,
    option_info          varchar(100) NULL,
    product_num          int    NOT NULL DEFAULT 1,
    product_detail_price int    NOT NULL DEFAULT 0,
    PRIMARY KEY (order_detail_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE address
(
    address_id     bigint       NOT NULL AUTO_INCREMENT,
    address_name   varchar(20) NOT NULL,
    address_phone varchar(30) NOT NULL,
    address_zipcode varchar(20) NOT NULL,
    address_main varchar(50) NOT NULL,
    address_sub varchar(50) NOT NULL,
    user_id        bigint       NOT NULL,
    PRIMARY KEY (address_id),
    CONSTRAINT fk_user_id   FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE comment
(
    comment_id     bigint       NOT NULL AUTO_INCREMENT,
    comment_content   varchar(50) NOT NULL,
    product_id      bigint      NOT NULL,
    user_id        bigint       NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);