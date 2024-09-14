CREATE TABLE IF NOT EXISTS DeviceType (
                            device_type_id SERIAL PRIMARY KEY,
                            type_name VARCHAR(255) NOT NULL,
                            description VARCHAR(255)
);

-- Создание таблицы Device
CREATE TABLE IF NOT EXISTS Device (
                        id SERIAL PRIMARY KEY,
                        device_name VARCHAR(255) NOT NULL,
                        status VARCHAR(50),
                        user_id INT,
                        device_type_id INT,
                        FOREIGN KEY (device_type_id) REFERENCES DeviceType(device_type_id)
);

-- Создание таблицы DeviceConfig
CREATE TABLE IF NOT EXISTS DeviceConfig (
                              id SERIAL PRIMARY KEY,
                              device_id INT,
                              config_key VARCHAR(255) NOT NULL,
                              config_value VARCHAR(255) NOT NULL,
                              FOREIGN KEY (device_id) REFERENCES Device(id)
);

-- Индекс для ускорения поиска по device_id в таблице DeviceConfig
CREATE INDEX IF NOT EXISTS idx_device_config_device_id ON DeviceConfig(device_id);

INSERT INTO DeviceType (type_name, description) VALUES
                                                    ('Smart Plug', 'Умная розетка для дистанционного управления электроприборами'),
                                                    ('Security Camera', 'Камера наблюдения для контроля за помещением'),
                                                    ('Heater', 'Обогреватель для поддержания комфортной температуры в помещении');
INSERT INTO Device (device_name, status, user_id, device_type_id)
VALUES ('TP-Link Smart Plug', 'ACTIVE', 1, 1);

INSERT INTO Device (device_name, status, user_id, device_type_id)
VALUES ('Nest Cam', 'ACTIVE', 2, 2);

INSERT INTO Device (device_name, status, user_id, device_type_id)
VALUES ('Dyson Heater', 'ACTIVE', 3, 2);
