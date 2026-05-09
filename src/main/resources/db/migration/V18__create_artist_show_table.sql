CREATE TABLE artist_show (

    artist_id BIGINT NOT NULL,

    show_id BIGINT NOT NULL,

    PRIMARY KEY (artist_id, show_id),

    CONSTRAINT fk_artist_show_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id),

    CONSTRAINT fk_artist_show_show
        FOREIGN KEY (show_id)
        REFERENCES shows(id)
);