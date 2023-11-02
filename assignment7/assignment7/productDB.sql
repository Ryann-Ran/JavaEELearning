DROP DATABASE IF EXISTS productDB;
CREATE DATABASE productDB CHARACTER SET utf8 COLLATE utf8_general_ci;
use productDB;

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


CREATE TABLE `supplier` (
  `id` bigint(20)  AUTO_INCREMENT NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `product_supplier` (
  `product_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
 CONSTRAINT `product_supplier_PK` PRIMARY KEY (`product_id`, `supplier_id`),
  CONSTRAINT `product_supplier_FK1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `product_supplier_FK2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
                        `id` varchar(255)  NOT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
                        `id` varchar(255)  NOT NULL,
                        `remark` varchar(255) DEFAULT "",
                        `authorities` varchar(255) DEFAULT "",
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_role` (
                             `user_id` varchar(255)  NOT NULL,
                             `role_id` varchar(255)  NOT NULL,
                             CONSTRAINT `user_role_PK` PRIMARY KEY (`user_id`, `role_id`),
                             CONSTRAINT `user_role_FK1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                             CONSTRAINT `user_role_FK2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `authority` (
                             `role_id` varchar(255)  NOT NULL,
                             `authority` varchar(255)  NOT NULL,
                             CONSTRAINT `authority_PK` PRIMARY KEY (`role_id`, `authority`),
                             CONSTRAINT `authority_FK3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `user` (id,password) values ("user1","$2a$10$TP6cKCz2g26LKFF/CHMi9elunslE6HRloOsotmr0Nh2B1y6DkraAK");
insert into `user` (id,password) values ("user2","$2a$10$TP6cKCz2g26LKFF/CHMi9elunslE6HRloOsotmr0Nh2B1y6DkraAK");
insert into `user` (id,password) values ("user3","$2a$10$TP6cKCz2g26LKFF/CHMi9elunslE6HRloOsotmr0Nh2B1y6DkraAK");
insert into `user` (id,password) values ("user4","$2a$10$TP6cKCz2g26LKFF/CHMi9elunslE6HRloOsotmr0Nh2B1y6DkraAK");

insert into `role` (id,authorities) values ("admin","product/admin,supplier/admin");
insert into `role` (id,authorities) values ("product_admin","product/admin");
insert into `role` (id,authorities) values ("supplier_admin","supplier/admin");
insert into `role` (id,authorities) values ("guest","");

insert into `user_role` (user_id,role_id) values ("user1","admin");
insert into `user_role` (user_id,role_id) values ("user2","product_admin");
insert into `user_role` (user_id,role_id) values ("user3","supplier_admin");
insert into `user_role` (user_id,role_id) values ("user4","guest");