DROP DATABASE IF EXISTS productDB2;

CREATE DATABASE productDB2 CHARACTER SET utf8 COLLATE utf8_general_ci;
use productDB2;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) AUTO_INCREMENT NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `stock_quantity` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20)  AUTO_INCREMENT NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_supplier`;
CREATE TABLE `product_supplier` (
  `product_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
 CONSTRAINT `PK` PRIMARY KEY (`product_id`, `supplier_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;