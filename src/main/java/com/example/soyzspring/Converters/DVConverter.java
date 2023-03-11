package com.example.soyzspring.Converters;

import com.example.soyzspring.Dto.DvDto;
import com.example.soyzspring.entity.DevicesVaporizers;
import org.springframework.stereotype.Component;

@Component
public class DVConverter {

    public DvDto entityToDto(DevicesVaporizers dv) {
        DvDto dvDto = new DvDto();
        dvDto.setDeviceId(dv.getDevicesId().getId());
        dvDto.setVaporizerId(dv.getVaporizersIdForDV().getId());
        return dvDto;
    }
}
