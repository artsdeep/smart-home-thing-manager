package ru.yandex.practicum.smarthome.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.smarthome.dto.HeatingSystemDto;
import ru.yandex.practicum.smarthome.entity.Device;
import ru.yandex.practicum.smarthome.entity.DeviceConfig;
import ru.yandex.practicum.smarthome.service.DeviceConfigService;
import ru.yandex.practicum.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.smarthome.service.KafkaService;


@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    private DeviceConfigService deviceConfigService;
    private final KafkaService kafkaService;



    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @GetMapping("/test")
    public String getHeatingSystem() {
        return "ddd";
    }
    @PostMapping("/registerDevice")
    public ResponseEntity<Device> registerDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.registerDevice(device);
        kafkaService.sendMessage("register-device", savedDevice.getDeviceName());
        return ResponseEntity.status(201).body(savedDevice);
    }

    @GetMapping("/getDeviceState/{deviceId}")
    public ResponseEntity<Device> getDeviceState(@PathVariable Long deviceId) {
        return deviceService.getDevice(deviceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/sendCommand")
    public ResponseEntity<String> saveOrUpdateConfig(
            @RequestParam Integer deviceId,
            @RequestParam String configKey,
            @RequestParam String configValue) {
            DeviceConfig updatedConfig = deviceConfigService.saveOrUpdateConfig(deviceId, configKey, configValue);

            return ResponseEntity.ok("Command sent to device " + deviceId);
    }

}
