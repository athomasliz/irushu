CREATE TABLE `mysql` (
`id` int NOT NULL,
`input` varchar(255) NOT NULL,
`output` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);
insert into `mysql` values( 1,  'A', 'B');
CREATE TABLE `user` (
  `id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT UC_username UNIQUE (`username`)
);
insert into `user` (`id`, `username`, `password`, `role`) values( 1, 'thomasli','$2a$10$P7uGVteoic43vKmSYbRcIOVOSRO2B5UfIoHIMRkXYK8cCkm6dJhGK','ROLE');
commit;
