-- liquibase formatted sql

--changeset gleidiin:#db.base-create-tables-db.sql:1

CREATE TABLE `question` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `identifier` varchar(60) NOT NULL COMMENT 'Name or base information for search',
    `content` varchar(1000) NOT NULL COMMENT 'Text that will show up on screen',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

CREATE TABLE `answer` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `id_question` bigint NOT NULL,
    `content` varchar(600) NOT NULL,
    `correct` TINYINT(1) NOT NULL DEFAULT 0,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_question`) REFERENCES question(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;


CREATE TABLE `template_challenge` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(120) NOT NULL,
    `description` varchar(360) NOT NULL,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY idx_unique_reference_code (`reference_code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

CREATE TABLE `template_challenge_question` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `id_template_challenge` bigint NOT NULL,
    `id_question` bigint NOT NULL,
    `order` SMALLINT NOT NULL,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_template_challenge`) REFERENCES template_challenge(`id`),
    FOREIGN KEY (`id_question`) REFERENCES question(`id`),
    UNIQUE KEY  (`id`, `id_question`, `order`),
    UNIQUE KEY  (`id_template_challenge`, `id_question`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

CREATE TABLE `challenge` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `id_template_challenge` bigint NOT NULL,
    `id_question` bigint NOT NULL COMMENT 'Current execution question, in order',
    `reference_code` varchar(30) NOT NULL,
    `started` TINYINT(1) NOT NULL DEFAULT 0,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime ON UPDATE CURRENT_TIMESTAMP,
    `finished_at` datetime,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_template_challenge`) REFERENCES template_challenge(`id`),
    FOREIGN KEY (`id_question`) REFERENCES question(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;