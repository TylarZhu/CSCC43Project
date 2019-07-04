CREATE TABLE listings (
	id INT PRIMARY KEY AUTO_INCREMENT,
    latitude INT NOT NULL,
    longitude INT NOT NULL,
    address TEXT NOT NULL,
    postal_code TEXT NOT NULL,
    list_type TEXT NOT NULL,
    price INT NOT NULL,
    amenities TEXT NOT NULL
);
