CREATE TABLE listings (
  id INT AUTO_INCREMENT,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  address TEXT NOT NULL,
  postal_code TEXT NOT NULL,
  list_type TEXT NOT NULL,
  price DOUBLE NOT NULL,
  amenities TEXT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE unavailable_times(
  date_id INT AUTO_INCREMENT,
  list_id INT,
  time DATE NOT NULL,
  PRIMARY KEY (date_id),
  FOREIGN KEY (list_id) REFERENCES listings (id)
);

INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities)
    VALUES (12, 12, '70 TOWN CENTER', '0B2 0P3', 'APERTMENT', 100000, 'AIR CONDITIONAR');
INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities)
    VALUES (12, 12, '70 TOWN CENTER', '0B2 0P9', 'CODO', 120000, 'AIR');

SELECT * FROM listings;

INSERT INTO unavailable_times VALUES (0, 1, '2019-12-8');
INSERT INTO unavailable_times (list_id, time) VALUES (2, '2018-1-8');
INSERT INTO unavailable_times (list_id, time) VALUES (2, '2018-1-9');
INSERT INTO unavailable_times (list_id, time) VALUES (2, '2018-1-10');

INSERT INTO unavailable_times (list_id, time) VALUES (1, '2019-12-9');
INSERT INTO unavailable_times (list_id, time) VALUES (1, '2019-12-10');



SELECT * FROM unavailable_times WHERE list_id = 1;

CREATE TABLE users(
    social_insurance_number INT,
    address TEXT NOT NULL,
    postal_code TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    occupation TEXT,
    comments TEXT,
    experence_rate INT,
    PRIMARY KEY (social_insurance_number)
);



DROP TABLE listings;

DROP TABLE unavailable_times;

DROP TABLE users;
