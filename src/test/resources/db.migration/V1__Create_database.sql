CREATE SCHEMA IF NOT EXISTS scheme;
SET SCHEMA_SEARCH_PATH scheme;

CREATE TABLE IF NOT EXISTS scheme.sensor
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS scheme.measurement
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    temperature      DECIMAL(5, 2) NOT NULL,
    is_raining BOOLEAN       NOT NULL,
    time_measurement       TIMESTAMP     NOT NULL,
    sensor_id  INT,
    FOREIGN KEY (sensor_id) REFERENCES scheme.sensor (id) ON DELETE RESTRICT
);