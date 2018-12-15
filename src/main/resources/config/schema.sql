DROP DATABASE IF EXISTS db_favoride;

CREATE DATABASE db_favoride CHARACTER SET 'utf8';
USE db_favoride;

DROP TABLE IF EXISTS user_usr;
CREATE TABLE user_usr (
  usr_id INT NOT NULL AUTO_INCREMENT,
  usr_email_address VARCHAR(50) NOT NULL,
  usr_first_name VARCHAR(50) NOT NULL,
  usr_last_name VARCHAR(50) NOT NULL,
  usr_password VARCHAR(100) NOT NULL,
  usr_phone_number CHAR(10) NOT NULL,
  usr_gender ENUM('M', 'F') NOT NULL,
  usr_birth_year INT(4) NOT NULL,
  usr_bio VARCHAR(250) NULL,
  PRIMARY KEY (usr_id)
);

DROP TABLE IF EXISTS journey_jrn;
CREATE TABLE journey_jrn (
  jrn_id INT NOT NULL AUTO_INCREMENT,
  usr_id INT NOT NULL,
  jrn_departure VARCHAR(50) NOT NULL,
  jrn_arrival VARCHAR(50) NOT NULL,
  jrn_date DATETIME NOT NULL,
  jrn_rate DECIMAL(4, 2) NOT NULL,
  jrn_seats INT(1) NOT NULL,
  PRIMARY KEY (jrn_id),
  FOREIGN KEY (usr_id) REFERENCES user_usr (usr_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS passenger_psg;
CREATE TABLE passenger_psg (
  jrn_id INT NOT NULL,
  usr_id INT NOT NULL,
  PRIMARY KEY(jrn_id, usr_id),
  FOREIGN KEY (jrn_id) REFERENCES journey_jrn(jrn_id) ON DELETE CASCADE,
  FOREIGN KEY (usr_id) REFERENCES user_usr(usr_id) ON DELETE CASCADE
);
