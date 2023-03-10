package com.example.soyzspring.Service;

import com.example.soyzspring.Dto.DvDto;
import com.example.soyzspring.Repository.DevicesRepository;
import com.example.soyzspring.Repository.DevicesVaporizersRepository;
import com.example.soyzspring.Repository.VaporizersRepository;
import com.example.soyzspring.entity.Devices;
import com.example.soyzspring.entity.DevicesVaporizers;
import com.example.soyzspring.entity.Vaporizers;
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

    public boolean isExists(Long deviceId, Long vaporizerId) {
        Devices devices = new Devices();
        Vaporizers vaporizers = new Vaporizers();
        devices.setId(deviceId);
        vaporizers.setId(vaporizerId);
        return devicesVaporizersRepository.findByDevicesIdAndVaporizersIdForDV(devices, vaporizers).isPresent();
    }
}
