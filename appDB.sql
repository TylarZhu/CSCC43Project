USE CSCC43Pro;
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
                      experience_rate INT,
                      PRIMARY KEY (social_insurance_number)
);
INSERT INTO users(social_insurance_number, address, postal_code, date_of_birth, occupation, comments, experience_rate)
VALUES (0220, '1265 Military Trail', 'M2J 1U7', '1996-01-01', 'NONE', 'GREAT', 5);



CREATE TABLE renters
(
    renter_id  INT PRIMARY KEY,
    renter_profile INT,
    FOREIGN KEY (renter_profile) REFERENCES users(social_insurance_number),
#     renter_address         TEXT NOT NULL REFERENCES users (address),
#     renter_poscode         TEXT NOT NULL REFERENCES users (postal_code),
#     renter_date_of_birth   DATE NOT NULL REFERENCES users (date_of_birth),
#     renter_occupation      TEXT REFERENCES users (occupation),
    target_id              INT,
    renter_experience_rate INT,
    payment_information    TEXT NOT NULL

);
INSERT INTO renters VALUES (1, 0220, 'good', 5, 1, 'credit');

SELECT * FROM renters
JOIN users
on social_insurance_number = renter_profile;

CREATE TABLE hosts(
    host_id INT PRIMARY KEY,
    host_profile INT,
    FOREIGN KEY (host_profile) REFERENCES users(social_insurance_number),
);

CREATE TABLE renter_list(
    listing_id INT PRIMARY KEY REFERENCES renters(renter_id),
    listing_history INT

);

CREATE TABLE future_booking(
    booking_id INT PRIMARY KEY REFERENCES renters(renter_id)
);

CREATE TABLE host_list(
    listing_id INT PRIMARY KEY REFERENCES hosts(host_id),
    rental_history TEXT

);





DROP TABLE listings;

DROP TABLE unavailable_times;

DROP TABLE users;

DROP TABLE renters;

DROP TABLE hosts;

DROP TABLE host_list;