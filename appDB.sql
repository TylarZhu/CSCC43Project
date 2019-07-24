CREATE TABLE IF NOT EXISTS listings (
  id INT AUTO_INCREMENT,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  address TEXT NOT NULL,
  postal_code TEXT NOT NULL,
  list_type TEXT NOT NULL,
  price DOUBLE NOT NULL,
  amenities TEXT NOT NULL,
  city TEXT NOT NULL,
  country TEXT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE unavailable_times(
  id INT AUTO_INCREMENT,
  list_id INT,
  times TEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (list_id) REFERENCES listings(id)
);

INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)
    VALUES (11, 11, '70 TOWN CENTER', '0B2 0P3', 'APARTMENT', 1, 'SELF_CHECK_IN', 'TORONTO', 'CANANDA');
INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)
    VALUES (9, 9, '80 TOWN CENTER', '0B2 0P10', 'BOUNTIQUE_HOTEL', 3, 'CARBON_MONOXIDE_DETECTOR', 'TORONTO', 'CANANDA');
INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)
    VALUES (10, 10, '100 TOWN CENTER', '0B2 0P1', 'BOUNTIQUE_HOTEL', 4, 'CARBON_MONOXIDE_DETECTOR', 'TORONTO', 'CANANDA');
INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)
    VALUES (10, 11, '110 TOWN CENTER', '0B2 0P6', 'BOUNTIQUE_HOTEL', 35, 'CARBON_MONOXIDE_DETECTOR', 'TORONTO', 'CANANDA');
INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)
    VALUES (11.5, 10.5, '10100 TOWN CENTER', '0B2 0P7', 'GUEST_SUIT', 2, 'BREAKFAST', 'TORONTO', 'CANANDA');

DELETE FROM listings WHERE id = 1;

SELECT * FROM listings ORDER BY price ASC;

SELECT * FROM listings  WHERE postal_code = '0B2 0P3' ORDER BY price ASC;

SELECT * From listings WHERE (SQRT(POWER((11 - longitude), 2) + POWER((11 - latitude), 2)) <= 3)
    ORDER BY (SQRT(POWER((11 - longitude), 2) + POWER((11 - latitude), 2)));


INSERT INTO unavailable_times (list_id, times) VALUES (2, '2018-01-08');
INSERT INTO unavailable_times (list_id, times) VALUES (2, '2018-01-09');
INSERT INTO unavailable_times (list_id, times) VALUES (2, '2018-01-10');
INSERT INTO unavailable_times (list_id, times) VALUES (1, '2019-07-29');
INSERT INTO unavailable_times (list_id, times) VALUES (3, '2019-07-20');
INSERT INTO unavailable_times (list_id, times) VALUES (4, '2019-12-10');

SELECT * FROM unavailable_times WHERE times = '2018-1-9';

SELECT * FROM unavailable_times;
SELECT * FROM listings;

SELECT * FROM unavailable_times NATURAL JOIN listings WHERE unavailable_times.list_id = listings.id;


SELECT * FROM unavailable_times WHERE list_id = 1;

CREATE TABLE IF NOT EXISTS users(
  social_insurance_number INT,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  address TEXT NOT NULL,
  postal_code TEXT NOT NULL,
  date_of_birth TEXT NOT NULL,
  occupation TEXT,
  PRIMARY KEY (social_insurance_number)
);

CREATE TABLE IF NOT EXISTS renters(
    renter_id  INT AUTO_INCREMENT,
    renter_profile INT,
    card_number TEXT NOT NULL ,
    card_expiry_date TEXT NOT NULL,
    cvv INT NOT NULL,
    PRIMARY KEY (renter_id),
    FOREIGN KEY (renter_profile) REFERENCES users(social_insurance_number)
);

CREATE TABLE hosts(
  host_id INT AUTO_INCREMENT,
  host_profile INT,
  FOREIGN KEY (host_profile) REFERENCES users(social_insurance_number),
  PRIMARY KEY (host_id)
);

SELECT * FROM users;
SELECT * FROM renters;
SELECT * FROM hosts;

INSERT users VALUES (1001, 'Zhu', 'Tylar', '90 town center', 'M1P 0B2', '1998-6-16', 'teacher');
INSERT users VALUES (1002, 'Zhou', 'Angel', '190 Brough Dr', 'M1P 0B2', '2000-5-18', 'psy');
INSERT users VALUES (1003, 'Huang', 'Oscar', '10011 town center', 'M1P 0B2', '1997-7-12', 'cs');
INSERT users VALUES (1004, 'Zhang', 'TianHua', '888 town center', 'M1P 0B9', '1945-7-12', 'pp');
INSERT users VALUES (1005, 'Ma', 'Yun', '999 town center', 'M1P 0B6', '1927-7-12', 'qq');
INSERT users VALUES (1006, 'Dong', 'AnJi', '111 town center', 'M1P 0B12', '1897-7-12', 'cc');

INSERT renters (renter_profile, card_number, card_expiry_date, cvv) VALUES (1001, '4505445736670000', '11/12', 315);
INSERT renters (renter_profile, card_number, card_expiry_date, cvv) VALUES (1002, '4505445736671111', '1/2', 123);
INSERT renters (renter_profile, card_number, card_expiry_date, cvv) VALUES (1003, '4505445736679999', '9/17', 456);

INSERT hosts (host_profile) VALUES (1004);
INSERT hosts (host_profile) VALUES (1005);
INSERT hosts (host_profile) VALUES (1006);

SELECT EXISTS(SELECT * FROM users WHERE social_insurance_number = 1006);

SELECT * FROM users NATURAL JOIN renters WHERE renters.renter_profile = users.social_insurance_number;
SELECT * FROM users NATURAL JOIN hosts WHERE hosts.host_profile = users.social_insurance_number;

CREATE TABLE IF NOT EXISTS commentTable(
    comment_id INT AUTO_INCREMENT,
    fromUsr INT,
    toUsr INT,
    content TEXT NOT NULL,
    rate INT NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (fromUsr) REFERENCES users(social_insurance_number),
    FOREIGN KEY (toUsr) REFERENCES users(social_insurance_number)
);

CREATE TABLE IF NOT EXISTS relationshipRenterHost(
    relation_id INT AUTO_INCREMENT,
    renter_ins INT,
    host_ins INT,
    list_id INT,
    PRIMARY KEY (relation_id),
    FOREIGN KEY (renter_ins) REFERENCES users(social_insurance_number),
    FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number),
    FOREIGN KEY (list_id) REFERENCES listings(id)
);

SELECT EXISTS(SELECT DISTINCT host_ins FROM relationshipRenterHost WHERE renter_ins = 1001);

SELECT EXISTS(SELECT * FROM users WHERE social_insurance_number = 1006);

SELECT * FROM relationshipRenterHost;

INSERT relationshipRenterHost (renter_ins, host_ins, list_id) VALUES (1001, 1005, 1);
INSERT relationshipRenterHost (renter_ins, host_ins, list_id) VALUES (1002, 1004, 2);
INSERT relationshipRenterHost (renter_ins, host_ins, list_id) VALUES (1003, 1004, 3);
INSERT relationshipRenterHost (renter_ins, host_ins, list_id) VALUES (1001, 1006, 4);

INSERT commentTable (fromUsr, toUsr, content, rate) VALUES (1001, 1002, 'love you so much!', 5);
INSERT commentTable (fromUsr, toUsr, content, rate) VALUES (1001, 1003, 'why are u living in here?', 1);
INSERT commentTable (fromUsr, toUsr, content, rate) VALUES (1002, 1003, 'Hahahaha', 4);
INSERT commentTable (fromUsr, toUsr, content, rate) VALUES (1002, 1001, 'love you too!', 5);

SELECT * FROM commentTable;

SELECT * FROM renters
JOIN users
on social_insurance_number = renter_profile;

CREATE TABLE IF NOT EXISTS hostOwnListings(
    id INT AUTO_INCREMENT,
    list_id INT,
    host_ins INT,
    PRIMARY KEY (id),
    FOREIGN KEY (list_id) REFERENCES listings(id),
    FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number)
);

INSERT hostOwnListings (host_ins, list_id) VALUES (1004, 1);
INSERT hostOwnListings (host_ins, list_id)  VALUES (1004, 2);
INSERT hostOwnListings (host_ins, list_id)  VALUES (1004, 3);
INSERT hostOwnListings (host_ins, list_id)  VALUES (1005, 4);
INSERT hostOwnListings (host_ins, list_id)  VALUES (1006, 5);

SELECT EXISTS(SELECT * FROM hostOwnListings WHERE host_ins = 1005 AND list_id = 4);

SELECT * FROM hostOwnListings;

SELECT list_id FROM hostOwnListings WHERE host_ins = 1;

CREATE TABLE IF NOT EXISTS rentalHistory(
    id INT AUTO_INCREMENT,
    host_ins INT,
    list_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number),
    FOREIGN KEY (list_id) REFERENCES listings(id)
);

SELECT host_ins FROM rentalHistory WHERE list_id = 1;

CREATE TABLE IF NOT EXISTS futureBooking(
    id INT AUTO_INCREMENT,
    renter_ins INT,
    list_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (list_id) REFERENCES listings(id),
    FOREIGN KEY (renter_ins) REFERENCES users(social_insurance_number)
);

SELECT renter_ins FROM futureBooking WHERE list_id = 1;

INSERT rentalHistory (host_ins, list_id) VALUES (1004, 1);
INSERT rentalHistory (host_ins, list_id) VALUES (1004, 2);
INSERT rentalHistory (host_ins, list_id) VALUES (1005, 3);

INSERT futureBooking (renter_ins, list_id) VALUES (1001, 1);
INSERT futureBooking (renter_ins, list_id) VALUES (1001, 2);
INSERT futureBooking (renter_ins, list_id) VALUES (1002, 3);

SELECT * FROM rentalHistory;

DELETE FROM rentalHistory WHERE list_id = 1;

DROP TABLE unavailable_times;

DROP TABLE hostOwnListings;

DROP TABLE rentalHistory;

DROP TABLE futureBooking;

DROP TABLE listings;

DROP TABLE commentTable;

DROP TABLE renters;

DROP TABLE hosts;

DROP TABLE relationshipRenterHost;

DROP TABLE users;
