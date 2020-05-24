SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id   int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO roles (name)
VALUES ('ROLE_EMPLOYEE'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         int(11)     NOT NULL AUTO_INCREMENT,
    username   varchar(50) NOT NULL,
    password   char(80)    NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    email      varchar(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO users (username, password, first_name, last_name, email)
VALUES ('alex', '$2a$10$CGamJWPYokRss2FX6WcBJOgFIDKyUoCYnrGtepfVQyDSTdngSxrzK', 'Alex', 'GeekBrains', 'alex@gb.com');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id int(11) NOT NULL,
    role_id int(11) NOT NULL,

    PRIMARY KEY (user_id, role_id),

    KEY FK_ROLE_idx (role_id),

    CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id         int(11)      NOT NULL AUTO_INCREMENT,
    title      varchar(80)  NOT NULL,
    short_info varchar(512) NOT NULL DEFAULT '',
    full_info  text,
    price      bigint       NOT NULL DEFAULT 0,
    unit       varchar(20)  NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;