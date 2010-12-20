CREATE DATABASE IF NOT EXISTS advBoard;
GRANT ALL PRIVILEGES ON advBoard.* TO 'hibernate'@'localhost' IDENTIFIED BY '123';

USE advBoard;

DROP TABLE IF EXISTS advertisements;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    UNIQUE (username)
) ENGINE=INNODB;

CREATE TABLE roles (
    user_id bigint(20) NOT NULL,
    role VARCHAR(50) NOT NULL,
    UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=INNODB;

CREATE TABLE advertisements (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id bigint(20),
    creationDate timestamp,
    message varchar(255),
    tags varchar(255) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=INNODB;

-- populate with simple test data
-- 1a1dc91c907325c69271ddf0c944bc72 = pass (md5)
-- 5ebe2294ecd0e0f08eab7690d2a6ee69 = secret (md5)

INSERT INTO users VALUES (1, 'kuklachev', '5ebe2294ecd0e0f08eab7690d2a6ee69');
INSERT INTO users VALUES (2, 'adv', '1a1dc91c907325c69271ddf0c944bc72');
INSERT INTO users VALUES (3, 'admin', '1a1dc91c907325c69271ddf0c944bc72');
INSERT INTO users VALUES (4, 'root', '1a1dc91c907325c69271ddf0c944bc72');

INSERT INTO roles VALUES (1, 'ADVERTISER');
INSERT INTO roles VALUES (2, 'ADVERTISER');
INSERT INTO roles VALUES (3, 'ADMIN');
INSERT INTO roles VALUES (4, 'ADMIN');
INSERT INTO roles VALUES (4, 'SECURITY_ADMIN');

INSERT INTO advertisements(user_id, creationDate, message, tags) VALUES (1, '2010-01-01 01:02:03.1234', 'Продам душу', 'other, spiritual');
INSERT INTO advertisements(user_id, creationDate, message, tags) VALUES (2, '2010-01-01 03:04:05', 'Куплю холодильник', 'equipment');
INSERT INTO advertisements(user_id, creationDate, message, tags) VALUES (2, '2010-01-01 00:00:00', 'will work for food', 'work');

-- SELECT * FROM users;

