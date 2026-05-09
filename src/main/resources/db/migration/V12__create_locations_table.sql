CREATE TABLE locations (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    slug VARCHAR(100) NOT NULL,

    designation VARCHAR(255) NOT NULL,

    address VARCHAR(255) NOT NULL,

    locality_id BIGINT NOT NULL,

    CONSTRAINT fk_locations_localities
        FOREIGN KEY (locality_id)
        REFERENCES localities(id)
);
