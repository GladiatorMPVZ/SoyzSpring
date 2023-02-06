package com.example.soyzspring.Converters;

import com.example.soyzspring.Dto.DeviceDto;
import com.example.soyzspring.Dto.VaporizerDto;
import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.Vaporizers;
import org.springframework.stereotype.Component;

@Component
public class VaporizerConverter {

    public VaporizerDto entityToDto(Vaporizers v) {
        VaporizerDto vaporizerDto = new VaporizerDto();
        vaporizerDto.setId(v.getId());
        vaporizerDto.setTitle(v.getTitle());
        return vaporizerDto;
    }
}
