package ru.yandex.practicum.smarthome.service;


import ru.yandex.practicum.smarthome.entity.Device;
import ru.yandex.practicum.smarthome.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device registerDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDevice(Long id) {
        return deviceRepository.findById(id);
    }
}
