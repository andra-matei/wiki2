CREATE SCHEMA IF NOT EXISTS `wikiweb`
  DEFAULT CHARACTER SET utf8;
USE `wikiweb`;

CREATE TABLE IF NOT EXISTS `wikiweb`.`article` (
  `id`    INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(1000)    DEFAULT NULL,
  `URL`   VARCHAR(1000)    DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `URL_UNIQUE` (`URL`),
  UNIQUE KEY `Title_UNIQUE` (`title`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `wikiweb`.`word` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(245)     DEFAULT NULL,
  `occurences` INT(11)          DEFAULT NULL,
  `idArticle`  INT(11) NOT NULL,
  PRIMARY KEY (`id`, `idArticle`),
  KEY `idArticle` (`idArticle`),
  CONSTRAINT `idArticle` FOREIGN KEY (`idArticle`) REFERENCES `article` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8