create database stationay;
use stationary;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
   `mobile` varchar(10) DEFAULT NULL,
 `dob` varchar(255) DEFAULT NULL,
  `hNo` varchar(255) DEFAULT NULL,
  `add1` varchar(255) DEFAULT NULL,
  `add2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
`pincode` varchar(6) DEFAULT NULL,
  `last_login` varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `orderhistory` (
  `id` int DEFAULT NULL,
  `sid` varchar(100) NOT NULL,
  `pay` varchar(100) DEFAULT NULL,
  `odate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `FK_Personhistory` (`id`),
  CONSTRAINT `FK_Personhistory` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `orderdetails` (
  `oid` varchar(25) NOT NULL,
  `oname` varchar(255) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `id` int DEFAULT NULL,
  `sid` varchar(100) DEFAULT NULL,
  `totalprice` varchar(100) DEFAULT NULL,
  KEY `FK_PersonOrder` (`id`),
  KEY `FK_orderhistory` (`sid`),
  CONSTRAINT `FK_orderhistory` FOREIGN KEY (`sid`) REFERENCES `orderhistory` (`sid`),
  CONSTRAINT `FK_PersonOrder` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `admin` (
  `id` int NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);



CREATE TABLE `book` (
`pId` varchar(20),
`pName` varchar(20),
`description` varchar(20),
`price` int(20),
`stock` int(20),
`author` varchar(20),
`noOfpages` int(20)
);

CREATE TABLE `pen` (
`pId` varchar(20),
`pName` varchar(20),
`description` varchar(20),
`price` int(20),
`stock` int(20),
`color` varchar(20),
`compName` varchar(20)
);

CREATE TABLE `desk` (
`pId` varchar(20),
`pName` varchar(20),
`description` varchar(20),
`price` int(20),
`stock` int(20),
`material` varchar(20),
`compName` varchar(20)
);

CREATE TABLE `calc` (
`pId` varchar(20),
`pName` varchar(20),
`description` varchar(20),
`price` int(20),
`stock` int(20),
`compName` varchar(20),
`type` varchar(20)
);


CREATE TABLE `complaint` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `issue` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `cdate` varchar(255) DEFAULT NULL,
  `oid` varchar(45) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `solved` (
  `scid` int NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `sdate` varchar(255) DEFAULT NULL,
  `solmsg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scid`),
  UNIQUE KEY `token` (`token`),
  CONSTRAINT `FK_complaintToken` FOREIGN KEY (`token`) REFERENCES `complaint` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;