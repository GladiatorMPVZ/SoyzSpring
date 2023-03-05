package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.DeviceConverter;
import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
import com.example.soyzspring.Exceptions.ResourceNotFoundException;
import com.example.soyzspring.Service.DevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/devices")
public class DevicesController {

    private final DevicesService devicesService;
    private final DeviceConverter deviceConverter;

    @GetMapping("/filtered")
    public ResponseEntity<List<SearchDevVapResult>> getSuitableDevices(@RequestParam String vaporizerTitle) {
        List<SearchDevVapResult> resultList = devicesService.searchDeviceResults(vaporizerTitle);
        return ResponseEntity.ok(resultList);
    }

    @GetMapping
    public List<DeviceDto> getAllDevices() {
        return devicesService.findAll().stream().map(deviceConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DeviceDto getDeviceById(@PathVariable Long id) {
        return deviceConverter.entityToDto(devicesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Устройство с id: " + id + " не найдено")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDevice(@RequestBody DeviceDto deviceDto) {
        devicesService.createNewDevice(deviceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDeviceById(@PathVariable Long id) {
        devicesService.deleteById(id);
    }
}
