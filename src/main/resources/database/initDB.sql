/*alter schema ${schema} default character set utf8mb4 default collate utf8mb4_unicode_ci;

create user if not exists '${username}'@'%' identified by '${password}';
grant all privileges on ${schema}.* to '${username}'@'%';

# Allow the application user to manage and replicate additional databases.
grant select, insert, update, file, delete, create, drop, index, execute, show view on *.* to '${username}'@'%';
grant lock tables, alter, system_variables_admin on *.* to '${username}'@'%';

flush privileges;*/

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
    `participants`        int,
    `scrummaster`        int  NOT NULL,
    `created_date`    datetime(6)  DEFAULT NULL,
    `settings`       mediumtext    DEFAULT NULL,
    `lifetime`      int DEFAULT NULL,
    CONSTRAINT `user_in_room` FOREIGN KEY (participants) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,

    UNIQUE KEY (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;