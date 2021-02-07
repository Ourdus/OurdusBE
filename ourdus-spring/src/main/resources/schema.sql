drop table if exists user;
drop table if exists product_category;
drop table if exists product;
drop table if exists product_option;

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
    PRIMARY KEY (option_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE address
(
    address_id     bigint       NOT NULL AUTO_INCREMENT,
    user_address   varchar(100) NOT NULL,
    user_id        bigint       NOT NULL,
    PRIMARY KEY (address_id),
    CONSTRAINT fk_user_id   FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);