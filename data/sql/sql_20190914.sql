create table `users`
(
    `id`            bigint(20) unsigned not null AUTO_INCREMENT,
    `email`         varchar(32)         NOT NULL,
    `nickname`      varchar(10)         NOT NULL,
    `avatar`        varchar(255)        NOT NULL DEFAULT '',
    `password`      varchar(128)        NOT NULL,
    `status`        tinyint(2)          NOT NULL DEFAULT '0',
    `last_login_at` timestamp           NOT NULL DEFAULT '0000-00-00 00:00:00',
    `github`        varchar(32)         NOT NULL DEFAULT '',
    `intro`         varchar(255)        NOT NULL DEFAULT '',
    `qq`            varchar(16)         NOT NULL DEFAULT '',
    `weibo`         varchar(32)         NOT NULL DEFAULT '',
    `created_at`    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_unique` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `posts`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`        bigint(20)                   DEFAULT NULL,
    `title`          varchar(200)        NOT NULL,
    `content`        mediumtext          NOT NULL,
    `read_num`       bigint(15)          NOT NULL DEFAULT '0',
    `comments_count` int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '评论数量',
    `user_fav_count` int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '用户喜欢数量',
    `published_at`   timestamp           NOT NULL DEFAULT '0000-00-00 00:00:00',
    `created_at`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `user_id_key` (`user_id`),
    KEY `published_at_key` (`published_at`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `post_tag`
(
    `post_id` bigint(20) NOT NULL,
    `tag_id`  bigint(20) NOT NULL,
    KEY `tag_id` (`tag_id`),
    KEY `post_id` (`post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_post_favorite`
(
    `user_id` bigint(20) NOT NULL,
    `post_id` bigint(20) NOT NULL,
    KEY `post_id` (`post_id`),
    KEY `user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `tags`
(
    `id`   bigint(20) unsigned not null AUTO_INCREMENT,
    `name` varchar(12)         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `post_comments`
(
    `id`         bigint(20) unsigned not null AUTO_INCREMENT,
    `user_id`    bigint(20) unsigned not null,
    `post_id`    bigint(20) unsigned not null,
    `comment_id` bigint(20) unsigned not null,
    `content`    varchar(255)        not null,
    `created_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp,
    PRIMARY KEY (`id`),
    KEY `post_id` (`post_id`),
    KEY `user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `admins`
(
    `id`            int(10) unsigned not null auto_increment,
    `username`      varchar(20)      not null,
    `password`      varchar(128)     not null,
    `last_login_at` timestamp        not null default current_timestamp,
    `last_login_ip` varchar(20)      not null,
    `created_at`    timestamp        not null default CURRENT_TIMESTAMP,
    `updated_at`    timestamp        not null default CURRENT_TIMESTAMP on update current_timestamp,
    `deleted_at`    timestamp,
    PRIMARY KEY (`id`),
    key `username` (`username`)
) ENGINE = InnoDB
  default charset = utf8mb4;

create table `roles`
(
    `id`   int(10) unsigned not null AUTO_INCREMENT,
    `name` varchar(20)      not null,
    primary key (`id`),
    key `name` (`name`)
) engine = InnoDB
  default charset = utf8mb4;

create table admin_role
(
    `admin_id` int(10) unsigned not null,
    `role_id`  int(10) unsigned not null,
    key `admin_id` (`admin_id`)
) engine = InnoDB
  default charset = utf8mb4;

create table permissions
(
    `id`            int(10) unsigned not null AUTO_INCREMENT,
    `name`          varchar(20)      not null,
    `http_method`   varchar(10)      not null,
    `uri`           varchar(255)     not null,
    `frontend_node` varchar(255)     not null,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table role_permission
(
    `role_id`       int(10) unsigned not null,
    `permission_id` int(10) unsigned not null,
    key `role_id` (`role_id`)
) engine = InnoDB
  default charset = utf8mb4;