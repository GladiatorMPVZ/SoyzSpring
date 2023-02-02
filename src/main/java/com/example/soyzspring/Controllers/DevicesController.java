package com.example.soyzspring.Controllers;


import com.example.soyzspring.Converters.DeviceConverter;
import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Service.DevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/devices")
public class DevicesController {

    private final DevicesService devicesService;
    private final DeviceConverter deviceConverter;

    @GetMapping
    public List<DeviceDto> getAllDevices() {
        return devicesService.findAll().stream().map(deviceConverter::entityToDto).collect(Collectors.toList());
    }
}
