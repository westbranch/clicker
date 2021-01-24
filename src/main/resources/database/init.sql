CREATE TABLE IF NOT EXISTS clicker
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    counter BIGINT,
    value_type VARCHAR(10),
    last_updated TIMESTAMP
);