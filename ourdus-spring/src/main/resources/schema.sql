CREATE TABLE user
(
    user_id     bigint      NOT NULL AUTO_INCREMENT,
--     user_status int(3) NOT NULL,
    user_email  varchar(20) NOT NULL,
    user_pw     varchar(20) NOT NULL,
    user_name   varchar(20) NOT NULL,
    user_tel    varchar(20) NOT NULL,
    reg_date    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_point  int         NOT NULL DEFAULT 0,
    writer_flag TINYINT(1) NOT NULL DEFAULT false,
    PRIMARY KEY (user_id),
    CONSTRAINT unique_user_email UNIQUE (user_email)
--     CONSTRAINT fk_user_status FOREIGN KEY (user_status) REFERENCES status (status) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- CREATE TABLE status
-- (
--     status int(1) NOT NULL,
--     status_description varchar(500) NULL
-- )


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
)

CREATE TABLE product_image
(
    image_id   bigint NOT NULL AUTO_INCREMENT,
    product_id bigint NOT NULL,
    image_file blob   NOT NULL,
    PRIMARY KEY (image_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
)

CREATE TABLE cart_detail
(
    cart_id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    author_id bigint NOT NULL,
    product_id bigint NOT NULL,
    option_info varchar(100) NULL,
    product_num int NOT NULL DEFAULT 1,
    cart_in_date datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    product_detail_price int NOT NULL DEFAULT 0,
    PRIMARY KEY (cart_id),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
)

CREATE TABLE order
(
    order_id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    order_date datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    order_price int NOT NULL DEFAULT 0,
    order_account varchar(50) NOT NULL,
    PRIMARY KEY (order_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
)

CREATE TABLE order_detail
(
    order_detail_id bigint NOT NULL AUTO_INCREMENT,
    order_id bigint NOT NULL,
    author_id bigint NOT NULL,
    product_id bigint NOT NULL,
    option_info varchar(100) NULL,
    product_num int NOT NULL DEFAULT 1,
    product_detail_price int NOT NULL DEFAULT 0,
    PRIMARY KEY (order_detail_id, order_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES order (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
)