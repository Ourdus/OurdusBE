CREATE TABLE user
(
    user_id     bigint      NOT NULL AUTO_INCREMENT,
--     user_status int(3) NOT NULL,
    user_email  varchar(20) NOT NULL,
    user_pw     varchar(20) NOT NULL,
    user_name   varchar(20) NOT NULL,
    user_tel    varchar(20) NOT NULL,
    reg_date    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_point  bigint      NOT NULL DEFAULT 0,
    writer_flag TINYINT(1)  NOT NULL DEFAULT false,
    PRIMARY KEY (user_id),
    CONSTRAINT unique_user_email UNIQUE (user_email)
--     CONSTRAINT fk_user_status FOREIGN KEY (user_status) REFERENCES status (status) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- CREATE TABLE status
-- (
--     status int(1) NOT NULL,
--     status_description varchar(500) NULL
-- )