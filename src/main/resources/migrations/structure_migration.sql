drop database if exists raf_news;
create database raf_news;

use raf_news;

-- USERS --
CREATE TABLE user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(100) UNIQUE,
    first_name     VARCHAR(100),
    last_name  VARCHAR(100),
    password VARCHAR(255),
    role     VARCHAR(50),
    status   VARCHAR(50)
);

-- CATEGORIES --
CREATE TABLE category
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);

-- NEWS --
CREATE TABLE news
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(500),
    content       VARCHAR(700),
    visited       INT,
    creation_time TIMESTAMP,
    author_id     INT,
    category_id   INT,
    FOREIGN KEY (author_id) REFERENCES user (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
-- COMMENTS --
CREATE TABLE comment
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    content       VARCHAR(500),
    creation_time TIMESTAMP,
    author_id       INT,
    news_id       INT,
    FOREIGN KEY (author_id) REFERENCES user (id),
    FOREIGN KEY (news_id) REFERENCES news (id),
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE
);

-- TAGS --
CREATE TABLE tag
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- NEWS-<NEWS_TAGS>-TAGS --
CREATE TABLE news_tag
(
    news_id INT,
    tag_id  INT,
    PRIMARY KEY (news_id, tag_id),
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);





