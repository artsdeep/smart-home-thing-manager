package ru.yandex.practicum.smarthome.service;

import jakarta.transaction.Transactional;
import ru.yandex.practicum.smarthome.entity.DeviceConfig;
import ru.yandex.practicum.smarthome.repository.DeviceConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DeviceConfigService {

    @Autowired
    private DeviceConfigRepository deviceConfigRepository;

    public List<DeviceConfig> getAllConfigs() {
        return deviceConfigRepository.findAll();
    }

    public DeviceConfig getConfigById(Long id) {
        return deviceConfigRepository.findById(id).orElse(null);
    }

    public DeviceConfig createConfig(DeviceConfig deviceConfig) {
        return deviceConfigRepository.save(deviceConfig);
    }

    public DeviceConfig updateConfig(Long id, DeviceConfig deviceConfig) {
        if (deviceConfigRepository.existsById(id)) {
            deviceConfig.setId(id);
            return deviceConfigRepository.save(deviceConfig);
        }
        return null;
    }

    public void deleteConfig(Long id) {
        deviceConfigRepository.deleteById(id);
    }
    @Transactional
    public DeviceConfig saveOrUpdateConfig(Integer deviceId, String configKey, String configValue) {
        // Пытаемся найти существующий параметр по deviceId и configKey
        Optional<DeviceConfig> existingConfig = deviceConfigRepository.findByDeviceIdAndConfigKey(deviceId, configKey);

        if (existingConfig.isPresent()) {
            // Если параметр найден, обновляем его значение
            DeviceConfig configToUpdate = existingConfig.get();
            configToUpdate.setConfigValue(configValue);
            return deviceConfigRepository.save(configToUpdate);
        } else {
            // Если параметр не найден, создаем новый
            DeviceConfig newConfig = new DeviceConfig();
            newConfig.setDeviceId(deviceId);
            newConfig.setConfigKey(configKey);
            newConfig.setConfigValue(configValue);
            return deviceConfigRepository.save(newConfig);
        }
    }
}
