package com.example.soyzspring.Converters;

import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.entity.Devices;
import org.springframework.stereotype.Component;

@Component
public class DeviceConverter {

    public DeviceDto entityToDto(Devices d) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(d.getId());
        deviceDto.setTitle(d.getTitle());
        return deviceDto;
    }
}
