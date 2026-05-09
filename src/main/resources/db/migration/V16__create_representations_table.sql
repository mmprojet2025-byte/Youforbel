CREATE TABLE representations (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    schedule DATETIME NOT NULL,

    show_id BIGINT NOT NULL,

    location_id BIGINT NOT NULL,

    CONSTRAINT fk_representations_shows
        FOREIGN KEY (show_id)
        REFERENCES shows(id),

    CONSTRAINT fk_representations_locations
        FOREIGN KEY (location_id)
        REFERENCES locations(id)
);