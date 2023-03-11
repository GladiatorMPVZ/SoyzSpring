package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.DeviceConverter;
import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.Exceptions.AppError;
import com.example.soyzspring.Exceptions.ResourceNotFoundException;
import com.example.soyzspring.ResultForms.SearchDevVapResult;
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
    public ResponseEntity<?> createNewDevice(@RequestBody DeviceDto deviceDto) {
        if (deviceDto.getTitle().length() == 0) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Название устройства не может быть пустым полем"), HttpStatus.BAD_REQUEST);
        }
        if (devicesService.isExists(deviceDto.getTitle())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такое устройство уже существует"), HttpStatus.BAD_REQUEST);
        }
        devicesService.createNewDevice(deviceDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteDeviceById(@PathVariable Long id) {
        devicesService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<?> updateTitle(@RequestBody VaporizerDto vaporizerDto) {
        devicesService.updateName(vaporizerDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
