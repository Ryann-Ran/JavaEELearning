DROP DATABASE IF EXISTS assignment5;

CREATE DATABASE assignment5 CHARACTER SET utf8 COLLATE utf8_general_ci;

USE assignment5;

CREATE TABLE `product` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(32) NOT NULL,
                           `price` float(11) NOT NULL,
                           `stockQuantity` float(11) NOT NULL,
                           `category` varchar(32) NOT NULL,
                           `productType` varchar(32) NOT NULL,
                           `description` varchar(32) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `product` VALUES (1, '小薯条', 8, 600, '食品', '快餐', '小份薯条');
INSERT INTO `product` VALUES (2, '大薯条', 10, 500, '食品', '快餐', '大份薯条');
INSERT INTO `product` VALUES (3, '汉堡', 15, 400, '食品', '快餐', '包含牛肉、酸黄瓜、生菜');
INSERT INTO `product` VALUES (4, '荣耀P90Pro', 3500, 400, '电子产品', '手机', '新款上市');
INSERT INTO `product` VALUES (5, '华为Mate60', 6000, 200, '电子产品', '手机', '遥遥领先');

CREATE TABLE `supplier` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(32) NOT NULL,
                            `city` varchar(32) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `supplier` VALUES (1, '麦当劳', '北京');
INSERT INTO `supplier` VALUES (2, '肯德基', '纽约');
INSERT INTO `supplier` VALUES (3, '荣耀', '上海');
INSERT INTO `supplier` VALUES (4, '华为', '南京');

CREATE TABLE `product_supplier` (
                                    `product_id` int(11) NOT NULL,
                                    `supplier_id` int(11) NOT NULL,
                                    PRIMARY KEY (`product_id`, `supplier_id`),
                                    FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
                                    FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `product_supplier` VALUES (1, 1);
INSERT INTO `product_supplier` VALUES (1, 2);
INSERT INTO `product_supplier` VALUES (2, 1);
INSERT INTO `product_supplier` VALUES (2, 2);
INSERT INTO `product_supplier` VALUES (3, 1);
INSERT INTO `product_supplier` VALUES (3, 2);
INSERT INTO `product_supplier` VALUES (4, 3);
INSERT INTO `product_supplier` VALUES (5, 4);