USE banco_generic;

CREATE TABLE `tb_user` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `email` varchar(130) NOT NULL,  
      `name` varchar(200) NOT NULL,  
      `password` varchar(255) NOT NULL,  
      PRIMARY KEY (`id`),  
      UNIQUE KEY `UK_4vih17mube9j7cqyjlfbcrk4m` (`email`)
);

CREATE TABLE `tb_role` (
   `id` bigint NOT NULL AUTO_INCREMENT,  
   `role_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `tb_user_role` (
    `user_id` bigint NOT NULL,  
     `role_id` bigint NOT NULL,  
     PRIMARY KEY (`user_id`,`role_id`),  
     KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),  
     CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),  
     CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
);
