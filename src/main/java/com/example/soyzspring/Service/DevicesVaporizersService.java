package com.example.soyzspring.Service;

import com.example.soyzspring.Dto.DvDto;
import com.example.soyzspring.Repository.DevicesRepository;
import com.example.soyzspring.Repository.DevicesVaporizersRepository;
import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.entity.DevicesVaporizers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevicesVaporizersService {

    private final DevicesVaporizersRepository devicesVaporizersRepository;
    private final VaporizersRepository vaporizersRepository;
    private final DevicesRepository devicesRepository;

    public void addNewParallel(DvDto dvDto) {
        DevicesVaporizers dv = new DevicesVaporizers();
        dv.setDevicesId(devicesRepository.getById(dvDto.getDeviceId()));
        dv.setVaporizersIdForDV(vaporizersRepository.getById(dvDto.getVaporizerId()));
        devicesVaporizersRepository.save(dv);
    }
}
