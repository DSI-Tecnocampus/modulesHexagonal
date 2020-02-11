DROP TABLE if EXISTS user_lab;
CREATE TABLE user_lab
(
  username VARCHAR (55) PRIMARY KEY,
  name VARCHAR (255),
  second_name VARCHAR (255),
  email VARCHAR (100),
  password VARCHAR(70) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1
);

DROP TABLE if EXISTS authorities;
CREATE TABLE authorities (
  authority_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (authority_id),
  UNIQUE KEY uni_username_role (role,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES user_lab (username));

DROP TABLE if EXISTS note_lab;
CREATE TABLE note_lab (
  id bigint auto_increment PRIMARY KEY,
  title VARCHAR (255) ,
  content VARCHAR (255),
  date_creation TIMESTAMP ,
  date_edit TIMESTAMP ,
  owner VARCHAR (55),
  FOREIGN KEY (owner)
  REFERENCES user_lab(username)
);

