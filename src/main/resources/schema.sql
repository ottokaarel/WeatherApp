DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        city_name VARCHAR(255),
                        updated_at VARCHAR(255),
                        temperature DECIMAL(10,2),
                        humidity INTEGER,
                        wind DECIMAL(10,2)
);