package ru.yandex.practicum.smarthome.repository;

import ru.yandex.practicum.smarthome.entity.DeviceConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeviceConfigRepository extends JpaRepository<DeviceConfig, Long> {
    Optional<DeviceConfig> findByDeviceIdAndConfigKey(Integer deviceId, String configKey);
}
