CREATE TABLE `user`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `uid`               varchar(70)  NOT NULL,
    `name`              varchar(62)  NOT NULL,
    `room_id`                   int  DEFAULT NULL,
    `created`       datetime(6)  DEFAULT NULL,
    `settings`       mediumtext  DEFAULT NULL,
    `is_scrummaster`     boolean DEFAULT NULL,
    UNIQUE KEY (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE `room`
(
    `id`          int  NOT NULL AUTO_INCREMENT,
    `room_number`        varchar(70)  NOT NULL,
    `scrummaster`        int  NOT NULL,
    `created_date`    datetime(6)  DEFAULT NULL,
    #`settings`       mediumtext    DEFAULT NULL,
    `lifetime`      int DEFAULT NULL,

    UNIQUE KEY (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

ALTER TABLE `user` ADD CONSTRAINT `fk_user_in_room` FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE ON UPDATE CASCADE;