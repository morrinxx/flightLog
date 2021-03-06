DROP TABLE log;
DROP TABLE drone;
DROP TABLE user;
CREATE TABLE drone (
  id INT not null,
  name VARCHAR(40) NOT NULL,
  description VARCHAR(100) NULL,
  available BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id)
);

CREATE TABLE user (
                      id VARCHAR(9) NOT NULL,
                      name VARCHAR(40) NULL,
                      description VARCHAR(100) NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE log (
  id INT NOT NULL AUTO_INCREMENT,
  droneID INT NULL,
  userID VARCHAR(9) NOT NULL,
  description VARCHAR(100) NULL,
  time_of_flight TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT drone_fk FOREIGN KEY (droneID) REFERENCES drone(id) ON DELETE NO ACTION,
  CONSTRAINT user_fk FOREIGN KEY (userID) REFERENCES user(id) ON DELETE NO ACTION
);


INSERT INTO user (id, name, description) VALUES ('franz', 'Franz Mueller', 'Einer der Tester');
INSERT INTO user (id, name, description) VALUES ('hans', 'Hans Berger', 'Atemberaubender Schach-spieler');
INSERT INTO user (id, name, description) VALUES ('peda', 'Peter Lagerhaus', 'Bekannt in ganz Hinterdupfing');

INSERT INTO drone (id, name, description) VALUES ('1', 'Drone-XS', 'Klein aber fein');
INSERT INTO drone (id, name, description) VALUES ('2', 'Drone-M', 'Standart');
INSERT INTO drone (id, name, description) VALUES ('3', 'Drone-XXL', 'Super Duper');

INSERT INTO log (droneID, userID, description, time_of_flight) VALUES (1, 'peda','Supa flug', '2019-06-10 18:52:15');
INSERT INTO log (droneID, userID, description, time_of_flight) VALUES (2, 'hans','Lässiger flug', '2017-06-10 18:52:15');
INSERT INTO log (droneID, userID, description, time_of_flight) VALUES (2, 'franz','Wahnsinns flug', '1975-06-10 18:52:15');
