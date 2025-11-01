CREATE DATABASE IF NOT EXISTS lending_system;

USE lending_system;

CREATE TABLE `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category` varchar(64) NOT NULL,
  `condition` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `available` tinyint NOT NULL DEFAULT '0',
  `createdon` datetime NOT NULL,
  `borrowed` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `loginid` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginid_UNIQUE` (`loginid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users(id,name,email,mobile,loginid,password,role) VALUES (1,'Administrator','admin@mail.com','9848012345','admin','admin@123','ADMIN');
INSERT INTO users(id,name,email,mobile,loginid,password,role) VALUES (2,'Staff','staff@mail.com','9848012345','staff','staff@123','STAFF');

CREATE TABLE `request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `equipmentid` int NOT NULL,
  `status` varchar(16) DEFAULT NULL,
  `startdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  `userid` int NOT NULL,
  `requestedby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_idx` (`userid`),
  KEY `equipmentref_idx` (`equipmentid`),
  CONSTRAINT `equipmentref` FOREIGN KEY (`equipmentid`) REFERENCES `equipment` (`id`),
  CONSTRAINT `userref` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;