CREATE TABLE DeviceType (
                            device_type_id SERIAL PRIMARY KEY,
                            type_name VARCHAR(255) NOT NULL,
                            description VARCHAR(255)
);

-- Создание таблицы Device
CREATE TABLE Device (
                        device_id SERIAL PRIMARY KEY,
                        device_name VARCHAR(255) NOT NULL,
                        status VARCHAR(50),
                        user_id INT,
                        device_type_id INT,
                        FOREIGN KEY (device_type_id) REFERENCES DeviceType(device_type_id)
);

-- Создание таблицы DeviceConfig
CREATE TABLE DeviceConfig (
                              config_id SERIAL PRIMARY KEY,
                              device_id INT,
                              config_key VARCHAR(255) NOT NULL,
                              config_value VARCHAR(255) NOT NULL,
                              FOREIGN KEY (device_id) REFERENCES Device(device_id)
);

-- Индекс для ускорения поиска по device_id в таблице DeviceConfig
CREATE INDEX idx_device_config_device_id ON DeviceConfig(device_id);
