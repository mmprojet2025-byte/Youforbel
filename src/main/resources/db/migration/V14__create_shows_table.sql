CREATE TABLE shows (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    slug VARCHAR(100) NOT NULL,

    title VARCHAR(255) NOT NULL,

    description TEXT,

    poster_url VARCHAR(255),

    location_id BIGINT,

    bookable BOOLEAN DEFAULT TRUE,

    price DECIMAL(10,2),

    created_at DATETIME,

    updated_at DATETIME,

    CONSTRAINT fk_shows_locations
        FOREIGN KEY (location_id)
        REFERENCES locations(id)
);
