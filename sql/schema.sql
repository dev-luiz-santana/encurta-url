CREATE DATABASE encurtaLink;

USE encurtaLink;

CREATE TABLE link(
	id_link int PRIMARY KEY AUTO_INCREMENT,
	link_original varchar(120) NOT NULL,
	codigo_link varchar(120) NOT NULL UNIQUE,
	acessos int DEFAULT 0
);