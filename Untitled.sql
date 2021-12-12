CREATE TABLE pet(
    name VARCHAR(10),
    id   VARCHAR(10) NOT NULL,
    species VARCHAR(10),
    pet_class VARCHAR(10),
    PRIMARY KEY (id)
);

CREATE TABLE price(
    price_in DOUBLE NOT NULL,
    pet_id VARCHAR(10) NOT NULL,
    PRIMARY KEY (pet_id),
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE
);
